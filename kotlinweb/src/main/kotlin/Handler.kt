import org.w3c.dom.*
import org.w3c.fetch.RequestInit
import kotlin.browser.document
import kotlin.browser.window
import kotlin.dom.*
import kotlin.js.Promise
import kotlin.js.json

object Handler {
    private const val testURL: String = "http://localhost:8080"
    private const val searchURL: String = "/events/findByQueryString?queryString="
    private var spinners: HashMap<HTMLInputElement, PriceCategory> = hashMapOf()
    var latestSearchQuery: String = ""
    private var latestBookingInfo: BookingInfo? = null


    private fun getSearchResults(searchQuery: String): Promise<List<EventDTO>> {
        return window.fetch(input = searchURL + searchQuery).then outerThen@{ res ->
            val text = res.text()
            return@outerThen Promise<Pair<String, Short>> { resolve, reject ->
                text.then { resolve(Pair(it, res.status)) }.catch(reject)
            }
        }.then {
            return@then when (it.second) {
                200.toShort() -> JSON.parse<Array<EventDTO>>(it.first).toList()
                204.toShort() -> emptyList()
                500.toShort() -> {
                    throw Exception("Server exception occurred: ${it.second}")
                }
                else -> {
                    throw Exception("Server exception occurred: ${it.second}")
                }
            }
        }.catch { trow ->
            console.log("Caught: $trow")
            throw trow
        }
    }

    private fun bookTickets(tickets: List<LocalTicket>): Promise<List<BookingResponse>> {
        if (tickets.isNotEmpty()) {
            val first = tickets.first()
            val rowSeats: MutableList<RowSeat> = mutableListOf()
            if (first.row != null) {
                tickets.forEach {
                    rowSeats.add(RowSeat(it.row!!.rowId, it.seat!!.seatId))
                }
            }
            console.info("Request for events/${first.event.eventId}/occurrences/${first.occurrence.occurrenceId}/categories/${first.category.eventCategoryId}")
            return window.fetch(input = "/events/${first.event.eventId}/occurrences/${first.occurrence.occurrenceId}/categories/${first.category.eventCategoryId}/book", init = RequestInit(
                    method = "POST",
                    headers = json().apply { this["Content-Type"] = "application/json" },
                    body = JSON.stringify(TicketPayload(tickets.size, latestBookingInfo!!, rowSeats.toTypedArray()))
            )).then outerThen@{ res ->
                val text = res.text()
                console.info("Response for events/${first.event.eventId}/occurrences/${first.occurrence.occurrenceId}/categories/${first.category.eventCategoryId}")
                return@outerThen Promise<Pair<String, Short>> { resolve, reject ->
                    text.then { resolve(Pair(it, res.status)) }.catch(reject)
                }
            }.then {
                return@then when (it.second) {
                    200.toShort(), 409.toShort() -> JSON.parse<Array<BookingResponse>>(it.first).toList()
                    500.toShort() -> {
                        throw Exception("Server exception occurred: ${it.second}")
                    }
                    else -> {
                        throw Exception("Server exception occurred: ${it.second}")
                    }
                }
            }.catch { trow ->
                console.log("Caught: $trow")
                throw trow
            }
        } else {
            return Promise { resolve, _ -> resolve(emptyList()) }
        }
    }

    private fun populateContainer(eventDTOs: List<EventDTO>) {
        val container = document.getElementById("container") as HTMLElement
        container.clear()
        if (eventDTOs.isNotEmpty()) {
            for (eventDTO in eventDTOs) {
                container.append(newEventDiv(eventDTO = eventDTO), document.createElement("hr"))
            }
            OnPageAlert.showInfo("Found ${eventDTOs.size} events")
        } else {
            container.appendElement("h3") { textContent = "No results found" }
            OnPageAlert.showInfo("No events found")
        }
    }

    private fun newEventDiv(eventDTO: EventDTO): Element {
        return document.createElement("div") {
            this.className = "event-element-container"
            val titleL = label(text = "Title", cssExt = "title")
            val title = span(text = eventDTO.title, cssExt = "title")
            val genreL = label(text = "Genre", cssExt = "genre")
            val genre = span(text = eventDTO.genere, cssExt = "genre")
            val occL = label(text = "Occurrences", cssExt = "occurrences")
            val occContainer = document.createElement("div") {
                this.className = "occ-container"
                this.appendElement("table") {
                    this.className = "occ-table"
                    val header = document.createElement("thead") {
                        this.append(occHeader())
                    }
                    val body = document.createElement("tbody")
                    val occurrences = eventDTO.occurrences
                    for (i in occurrences.indices) {
                        console.dir(occurrences[i])
                        body.appendChild(occRow(eventDTO, occurrences[i]))
                    }
                    this.appendChild(header)
                    this.appendChild(body)
                }
            }
            this.append(titleL, title, br(), genreL, genre, br(), occL, br(), occContainer)
        }
    }

    private fun occHeader(): Element {
        val names = listOf("Date", "Time", "Location", "Type", "Price", "Action")
        return document.createElement("th") {
            this.className = "occ-table-header"
            this.append(document.createElement("tr") {
                for (name in names) {
                    this.append(
                            document.createElement("th") {
                                this.textContent = name
                            }
                    )
                }
            })
        }
    }

    private fun occRow(event: EventDTO, occurrence: Occurrence): Element {
        console.log("exec")
        return document.createElement("tr") {
            this.className = "occ-row"
            this.append(
                    td(span(occurrence.date.toDateString(), "occ-row-col-date")),
                    td(span(occurrence.time.toTimeString(), "occ-row-col-time")),
                    td(span(occurrence.toLocationString(), "occ-row-col-location")),
                    td(span(occurrence.categoryCalcDataDTO.ticketTypes, "occ-row-col-type")),
                    td(span(occurrence.categoryCalcDataDTO.priceRangeString, "occ-row-col-price")),
                    td(document.createElement("button").also {
                        it.textContent = "Show Details"
                        it.addEventListener(type = "click", callback = {
                            showDetail(event, occurrence)
                        })
                    })
            )
        }
    }

    private fun showDetail(event: EventDTO, occurrence: Occurrence) {
        console.log("Detail for ${event.eventId} - Occ: ${occurrence.occurrenceId}")
        val container = document.getElementById("container")!!
        container.clear()
        container.append(detailView(event, occurrence))
        updateTotal()
    }

    private fun detailView(event: EventDTO, occurrence: Occurrence): Element {
        return document.createElement("div") {
            className = "detail-div-root"
            append(detailViewInfo(event, occurrence))
            val pair = detailViewCategories(occurrence)
            append(pair.first)
            spinners = pair.second as HashMap<HTMLInputElement, PriceCategory>
            appendElement("div") { id = "detail-total-view" }
            append(detailViewActions(event, occurrence))
        }
    }

    private fun detailViewActions(event: EventDTO, occurrence: Occurrence): Element {
        return document.createElement("div") {
            appendElement("button") {
                val btn = this as HTMLButtonElement
                btn.textContent = "Add to cart"
                btn.addEventListener(type = "click", callback = {
                    val count = addCurrentToCart(event, occurrence)
                    if (count > 0) {
                        OnPageAlert.showInfo("Added $count items to cart.")
                        openCart()
                    }
                })
            }
        }
    }

    private fun addCurrentToCart(event: EventDTO, occurrence: Occurrence): Int {
        var count = 0
        for (spinner in spinners) {
            val amount = spinner.key.value.toInt()
            if (spinner.value.rows == null) {
                if (amount <= spinner.value.totalTickets - spinner.value.usedTickets) {
                    for (i in 0 until amount) {
                        Cart.add(LocalTicket(event, occurrence, spinner.value))
                        count++
                    }
                } else {
                    spinner.key.value = spinner.key.max
                }
            } else {
                console.log("Adding seats to cart")
                currentDetailedSeats[spinner.value.eventCategoryId]?.forEach {
                    val first = spinner.value.rows.first { row -> row.seats.any { seat -> seat.seatId == it.seatId } }
                    Cart.add(LocalTicket(event, occurrence, spinner.value, first, it))
                    count++
                }
            }
        }
        return count
    }

    private fun detailViewCategories(occurrence: Occurrence): Pair<Element, MutableMap<HTMLInputElement, PriceCategory>> {
        val map = mutableMapOf<HTMLInputElement, PriceCategory>()
        currentDetailedSeats = mutableMapOf()
        val createElement = document.createElement("div") {
            occurrence.priceCategories.forEach {
                console.dir(it)
                append(
                        if (it.rows != null) {
                            seatSeats(it, map)
                        } else {
                            freeSeats(it, map)
                        }
                )
            }
        }
        return Pair(createElement, map)
    }

    var currentDetailedSeats: MutableMap<Int, MutableList<Seat>> = mutableMapOf()

    private fun seatSeats(cat: PriceCategory, map: MutableMap<HTMLInputElement, PriceCategory>): Element {
        return document.createElement("div") {
            val hiddenSpinner = document.createElement("input") {
                this as HTMLInputElement
                type = "hidden"
                value = "0"
            } as HTMLInputElement
            append(hiddenSpinner)
            map[hiddenSpinner] = cat
            appendElement("div") {
                cat.rows.forEach {
                    appendElement("div") {
                        append(
                                label(it.rowIdf)
                        )
                        it.seats.forEach {
                            appendElement("button") {
                                textContent = it.seatIdf
                                var marked = !it.isFree
                                currentDetailedSeats[cat.eventCategoryId] = mutableListOf()
                                if (!marked) {
                                    addEventListener(type = "click", callback = { _ ->
                                        if (marked) {
                                            marked = false
                                            classList.remove("marked")
                                            hiddenSpinner.value = hiddenSpinner.value.toInt().dec().toString()
                                            currentDetailedSeats[cat.eventCategoryId]?.remove(it)
                                        } else {
                                            classList.add("marked")
                                            marked = true
                                            hiddenSpinner.value = hiddenSpinner.value.toInt().inc().toString()
                                            currentDetailedSeats[cat.eventCategoryId]?.add(it)
                                        }
                                        updateTotal()
                                    })
                                } else {
                                    classList.add("blocked")
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun freeSeats(cat: PriceCategory, map: MutableMap<HTMLInputElement, PriceCategory>): Element {
        return document.createElement("div") {
            val spinner = document.createElement("input") as HTMLInputElement
            spinner.type = "number"
            spinner.min = "0"
            spinner.max = "${cat.totalTickets - cat.usedTickets}"
            spinner.step = "1"
            spinner.value = "0"
            spinner.oninput = { _ ->
                try {
                    val toInt = spinner.value.toInt()
                    if (toInt > cat.totalTickets - cat.usedTickets) {
                        spinner.value = spinner.max
                    }
                    updateTotal()
                } catch (e: NumberFormatException) {
                    spinner.value.replace("\\D", "")
                }
            }
            map[spinner] = cat
            append(
                    span(cat.categoryName, "cat-name"),
                    span(cat.toDisplayPrice(), "cat-price"),
                    span(cat.ticketRange(), "cat-ticket-range"),
                    spinner
            )
        }
    }

    private fun updateTotal() {
        var totalPrice = 0
        var ticketAmount = 0
        for (spinner in spinners) {
            val tickets = spinner.key.value.toInt()
            ticketAmount += tickets
            totalPrice += tickets * spinner.value.priceInCents

        }
        val totalView = document.getElementById("detail-total-view")!!
        totalView.clear()
        totalView.append(
                label("Total", "cat-total-price-label"),
                span("${totalPrice / 100.0}€", "cat-total-price"),
                label("Amount", "cat-total-amount-label"),
                span("$ticketAmount", "cat-total-amount")
        )
    }

    private fun detailViewInfo(event: EventDTO, occurrence: Occurrence): Element {
        return document.createElement("div") {
            this.appendElement("h3") {
                this.textContent = "More Information"
            }
            this.appendElement("ul") {
                append(
                        li(
                                label("Title"),
                                span(event.title)
                        ),
                        li(
                                label("Date"),
                                span(occurrence.date.toDateString()),
                                span(occurrence.time.toTimeString())
                        ),
                        li(
                                label("Location"),
                                span(occurrence.toLocationString())
                        ),
                        li(
                                label("Description"),
                                span(event.description)
                        ),
                        li(
                                label("Genre"),
                                span(event.genere)
                        ),
                        li(
                                label("Artists"),
                                span(event.artistNames.joinToString(separator = ", "))
                        )
                )
            }
        }
    }

    private fun li(vararg child: Element): Element {
        return document.createElement("li") {
            for (c in child) {
                this.append(c)
            }
        }
    }

    private fun td(child: Element): Element {
        return document.createElement("td") {
            this.append(child)
        }
    }

    private fun span(text: String, cssExt: String = ""): Element {
        return document.createElement("span") {
            this.className = "event-element-$cssExt"
            this.textContent = text
        }
    }

    private fun label(text: String, cssExt: String = ""): Element {
        return document.createElement("span") {
            this.className = "event-element-$cssExt-label"
            this.textContent = "$text: "
        }
    }

    private fun br(): Element {
        return document.createElement("br")
    }

    private fun Occurrence.toLocationString() = "$country, $city"
    private fun PriceCategory.toDisplayPrice() = "${priceInCents / 100.0}€"
    private fun PriceCategory.ticketRange(): String {
        return when (this.usedTickets) {
            null, undefined -> ""
            else -> "$usedTickets / $totalTickets"
        }
    }

    private fun Date.toDateString() = "$year-$month-$dayOfMonth"
    private fun Time.toTimeString() = "$hour:$minute"

    fun updateCart() {
        val cartContainer = document.getElementById("cart")!!
        cartContainer.clear()
        val asList = Cart.asList()
        if (asList.isNotEmpty()) {
            for (item in asList) {
                if (item.isNotEmpty()) {
                    val first = item.first()
                    cartContainer.appendElement("div") {
                        className = "cart-row"
                        append(
                                span(first.event.title, "cart-evt-title"),
                                span(first.occurrence.date.toDateString() + ":" + first.occurrence.time.toTimeString(), "cart-date-time"),
                                span(first.category.categoryName, "cart-cat-name"),
                                span((first.category.priceInCents / 100.0).toString(), "cart-cat-price"),
                                span(item.size.toString(), "cart-cat-amount-tickets")
                        )
                    }
                }
            }
            cartContainer.appendElement("button") {
                this.textContent = "Buy"
                this.className = "cart-buy"
                addEventListener(type = "click", callback = {
                    showPaymentInfo()
                })
            }
        } else {
            cartContainer.appendElement("h3") {
                this.textContent = "Your Cart is Empty"
            }

        }
    }

    private fun showPaymentInfo() {
        openTab("paymentInfo")
        fillLatestPaymentInfo()
    }

    private fun showResult(bookedTickets: MutableMap<List<LocalTicket>, List<BookingResponse>>, errors: MutableMap<List<LocalTicket>, BookingResponse>) {
        openTab("res")
        (document.getElementById("suc") as HTMLDivElement).apply {
            var count = 0
            clear()
            bookedTickets.entries.forEachIndexed { index, mutableEntry ->
                appendElement("div") {
                    append(
                            span("#$index", ""),
                            span(mutableEntry.key.first().event.title, ""),
                            span(mutableEntry.key.first().occurrence.date.toDateString() + " - " + mutableEntry.key.first().occurrence.time.toTimeString(), ""),
                            span(mutableEntry.key.first().category.categoryName, ""),
                            br(),
                            label("Transaction Ids", ""),
                            br()
                    )
                    appendElement("div") {
                        mutableEntry.value.forEachIndexed { index, bookingResponse ->
                            count++
                            append(
                                    span("${index + 1}:\t\t", ""),
                                    span("ID: ${bookingResponse.tranactionId}", ""),
                                    br()
                            )
                        }
                    }
                }
            }
            if (count > 0) {
                OnPageAlert.showSuc("Booked $count tickets.")
            }
        }
        (document.getElementById("err") as HTMLDivElement).apply {
            clear()
            errors.entries.forEachIndexed { index, mutableEntry ->
                appendElement("div") {
                    append(
                            span("#$index", ""),
                            span(mutableEntry.key.first().event.title, ""),
                            span(mutableEntry.key.first().occurrence.date.toDateString() + " - " + mutableEntry.key.first().occurrence.time.toTimeString(), ""),
                            span(mutableEntry.key.first().category.categoryName, ""),
                            br()
                    )
                    appendElement("div") div@{
                        mutableEntry.value.apply {
                            this@div.append(
                                    span("Failed due to: ${this.errMsg}", "")
                            )
                        }
                    }
                }
            }
        }
    }

    private fun fillLatestPaymentInfo() {
        val cardInput = document.getElementById("cardNumber") as HTMLInputElement
        if (latestBookingInfo != null) {
            cardInput.value = latestBookingInfo?.cardNr.toString()
        }
        val cityInput = document.getElementById("city") as HTMLInputElement
        if (latestBookingInfo != null) {
            cityInput.value = latestBookingInfo?.city.toString()
        }
        val countryInput = document.getElementById("country") as HTMLInputElement
        if (latestBookingInfo != null) {
            countryInput.value = latestBookingInfo?.country.toString()
        }
        val houseInput = document.getElementById("house") as HTMLInputElement
        if (latestBookingInfo != null) {
            houseInput.value = latestBookingInfo?.house.toString()
        }
        val nameFInput = document.getElementById("nameF") as HTMLInputElement
        if (latestBookingInfo != null) {
            nameFInput.value = latestBookingInfo?.nameF.toString()
        }
        val nameLInput = document.getElementById("nameL") as HTMLInputElement
        if (latestBookingInfo != null) {
            nameLInput.value = latestBookingInfo?.nameL.toString()
        }
        val streetInput = document.getElementById("street") as HTMLInputElement
        if (latestBookingInfo != null) {
            streetInput.value = latestBookingInfo?.street.toString()
        }
        val zipInput = document.getElementById("zip") as HTMLInputElement
        if (latestBookingInfo != null) {
            zipInput.value = latestBookingInfo?.zip.toString()
        }
    }

    private fun updateLatestPaymentInfo() {
        //Card
        val cardInput = document.getElementById("cardNumber") as HTMLInputElement
        val cardNumber = cardInput.value
        //city
        val cityInput = document.getElementById("city") as HTMLInputElement
        val city = cityInput.value
        //country
        val countryInput = document.getElementById("country") as HTMLInputElement
        val country = countryInput.value
        //house
        val houseInput = document.getElementById("house") as HTMLInputElement
        val house = houseInput.value
        //First Name
        val nameFInput = document.getElementById("nameF") as HTMLInputElement
        val nameF = nameFInput.value
        //Last Name
        val nameLInput = document.getElementById("nameL") as HTMLInputElement
        val nameL = nameLInput.value
        //street
        val streetInput = document.getElementById("street") as HTMLInputElement
        val street = streetInput.value
        //zip
        val zipInput = document.getElementById("zip") as HTMLInputElement
        val zip = zipInput.value
        var emptyFields: String = ""
        val empty = isEmpty(
                Pair(cardNumber, "Card Number"),
                Pair(city, "City Name"),
                Pair(nameF, "First Name"),
                Pair(nameL, "Last Name"),
                Pair(street, "Street Name"),
                Pair(zip, "Zip code")
        )
        try {
            emptyFields = empty.joinToString(separator = ", ", prefix = "The field(s) for ", postfix = " may not be empty")
            if (cardNumber.isNotEmpty()) {
                val bookingInfo = BookingInfo(cardNumber.toInt(), city, country, house, nameF, nameL, street, zip)
                if (empty.isNotEmpty()) {
                    OnPageAlert.showErr(emptyFields)
                } else {
                    latestBookingInfo = bookingInfo
                    OnPageAlert.clear()
                }
            } else {
                if (empty.isNotEmpty()) {
                    OnPageAlert.showErr(emptyFields)
                }
            }
        } catch (e: NumberFormatException) {
            if (empty.isNotEmpty()) {
                OnPageAlert.showErr("Card Number is not a number and $emptyFields")
            } else {
                OnPageAlert.showErr("Card Number is not a number")
            }
        }
        console.dir("latestBookingInfo = $latestBookingInfo")
    }

    private fun isEmpty(vararg arr: Pair<String, String>): MutableList<String> {
        return arr.map { if (it.first.isNotEmpty()) "" else it.second }.filter { it.isNotEmpty() }.toCollection(mutableListOf())
    }

    fun updateBrowser(): Promise<Boolean> {
        return getSearchResults(latestSearchQuery).then {
            populateContainer(it)
            return@then it.isNotEmpty()
        }.catch {
            throw it
        }
    }

    fun confBtnSetup(){
        val htmlButtonElement = document.getElementById("confirmBtn") as HTMLButtonElement
        htmlButtonElement.removeEventListener(type = "click", callback = {
            console.info("Removed listener")
        })
        htmlButtonElement.addEventListener(type = "click", callback = {
            console.info("ConfirmBtn pressed")
            updateLatestPaymentInfo()
            val bookedTickets: MutableMap<List<LocalTicket>, List<BookingResponse>> = mutableMapOf()
            val errors = mutableMapOf<List<LocalTicket>, BookingResponse>()
            val asList = Cart.asList()
            val promise: Promise<Any> = Promise() { resolve, _ ->
                var resolvesNeeded = 0
                asList.forEachIndexed { index, it ->
                    console.info(index)
                    console.dir(it)
                    bookTickets(it).then { list ->
                        if (list.size == it.size) {
                            console.info("Same length ${list.size} == ${it.size}\"")
                            var bool = true
                            list.forEach { res ->
                                console.info(res.errMsg)
                                //bool = res.errMsg != null && res.tranactionId != null && res.tranactionId.isNotEmpty()
                            }
                            if (bool) {
                                bookedTickets[it] = list
                            } else {
                                errors[it] = list.first()
                            }
                        } else {
                            console.info("Dif len: ${list.size} != ${it.size}")
                            errors[it] = list.first()
                        }
                        resolvesNeeded++
                        if (resolvesNeeded == asList.size) {
                            resolve(Any())
                        }
                        console.info("Resolved $index")
                    }.catch { throwable ->
                        OnPageAlert.showErr("Exception occurred: ${throwable.message}")
                    }
                }
            }
            promise.then {
                console.log("Now")
                bookedTickets.keys.forEach { list -> list.forEach { item -> Cart.remove(item) } }
                if (Cart.isEmpty()) {
                    showResult(bookedTickets, mutableMapOf())
                } else {
                    showResult(bookedTickets, errors)
                }
            }
        })
    }
}


fun openCart() {
    Handler.updateCart()
    openTab("cart")
}

fun openBrowser() {
    Handler.updateBrowser().then {
        if (it) {
            openTab("container")
        }
    }.catch {
        OnPageAlert.showErr("Could not query search results - ${it.message!!}")
    }
}

fun openTab(id: String) {
    val elementsByClassName = document.getElementsByClassName("tabcontent")
    for (i in 0 until elementsByClassName.length) {
        val element = elementsByClassName[i]
        element!!.addClass("none")
        if (element.id == id) {
            element.removeClass("none")
        }
    }
}

fun main() {
    /* Notification code if needed
    //Request Permissions
    if (Notification.permission != NotificationPermission.GRANTED) {
        console.log("Permission Missing")
        Notification.requestPermission().then {
            if (it == NotificationPermission.GRANTED) {
                console.info("Notifications active")
                Notification("Activated Notifications")
            } else {
                console.info("Not granted")
            }
        }
    } else {
        console.info("Notifications active")
        Notification("Active Notifications")
    }
     */
    //Setup tab buttons
    (document.getElementById("searchTabBtn") as HTMLButtonElement)
            .addEventListener(type = "click", callback = {
                openTab("search")
            })
    (document.getElementById("containerBtn") as HTMLButtonElement)
            .addEventListener(type = "click", callback = {
                openBrowser()
            })
    (document.getElementById("cartBtn") as HTMLButtonElement)
            .addEventListener(type = "click", callback = {
                openCart()
            })
    //Open default tab
    openTab("search")
    (document.getElementById("searchBtn") as HTMLButtonElement)
            .addEventListener(type = "click", callback = {
                generateSearchQuery()
                openBrowser()
            })
    val resetSearchButton = document.getElementById("resetBtn") as HTMLButtonElement
    resetSearchButton.addEventListener(type = "click", callback = {
        val searchEvtTitle = document.getElementById("event-title") as HTMLInputElement
        val withFromCB = document.getElementById("withFrom") as HTMLInputElement
        val fromDatePick = document.getElementById("fromDate") as HTMLInputElement
        val withTillCB = document.getElementById("withTill") as HTMLInputElement
        val tillDatePicker = document.getElementById("tillDate") as HTMLInputElement
        val genreIn = document.getElementById("genre") as HTMLInputElement
        val artistIn = document.getElementById("artist") as HTMLInputElement
        val locationIn = document.getElementById("location") as HTMLInputElement

        searchEvtTitle.value = ""
        withFromCB.checked = false
        withTillCB.checked = false
        fromDatePick.value = ""
        tillDatePicker.value = ""
        genreIn.value = ""
        artistIn.value = ""
        locationIn.value = ""
    })
    Handler.confBtnSetup()
}

fun String.append(s: String): String {
    return this.plus(s)
}

fun generateSearchQuery() {
    val searchEvtTitle = document.getElementById("event-title") as HTMLInputElement
    val withFromCB = document.getElementById("withFrom") as HTMLInputElement
    val fromDatePick = document.getElementById("fromDate") as HTMLInputElement
    val withTillCB = document.getElementById("withTill") as HTMLInputElement
    val tillDatePicker = document.getElementById("tillDate") as HTMLInputElement
    val genreIn = document.getElementById("genre") as HTMLInputElement
    val artistIn = document.getElementById("artist") as HTMLInputElement
    val locationIn = document.getElementById("location") as HTMLInputElement

    var sb = ""
    if (withFromCB.checked && fromDatePick.value.isNotEmpty()) {
        sb = sb.append("-").append(SearchCategories.DATE_FROM.getIdf()).append("=\"").append(fromDatePick.value.toSearchDate()).append("\"")
    }
    if (withTillCB.checked && tillDatePicker.value.isNotEmpty()) {
        sb = sb.append("-").append(SearchCategories.DATE_UNTIL.getIdf()).append("=\"").append(tillDatePicker.value.toSearchDate()).append("\"")
    }
    if (searchEvtTitle.value.isNotEmpty()) {
        sb = sb.append("-").append(SearchCategories.EVENT_NAME.getIdf()).append("=\"").append(searchEvtTitle.value).append("\"")
    }
    if (artistIn.value.isNotEmpty()) {
        sb = sb.append("-").append(SearchCategories.ARTIST_NAME.getIdf()).append("=\"").append(artistIn.value).append("\"")
    }
    if (genreIn.value.isNotEmpty()) {
        sb = sb.append("-").append(SearchCategories.GENRE.getIdf()).append("=\"").append(genreIn.value).append("\"")
    }
    if (locationIn.value.isNotEmpty()) {
        sb = sb.append("-").append(SearchCategories.LOCATION.getIdf()).append("=\"").append(locationIn.value).append("\"")
    }
    Handler.latestSearchQuery = sb
}

fun String.toSearchDate(): String {
    return this@toSearchDate.split('-').reversed().joinToString(separator = ".")
}

enum class SearchCategories(private val catIdf: String) {
    DATE_FROM("from"), DATE_UNTIL("until"),
    EVENT_NAME("title"), ARTIST_NAME("name"),
    GENRE("genre"), LOCATION("location");

    fun getIdf(): String {
        return catIdf
    }
}

object Cart {
    private val items: MutableMap<Int, MutableList<LocalTicket>> = mutableMapOf()

    fun asList(): List<List<LocalTicket>> {
        val outerList: MutableList<List<LocalTicket>> = mutableListOf()
        for (item in items) {
            outerList.add(item.value)
        }
        return outerList
    }

    fun add(localTicket: LocalTicket) {
        if (items.contains(localTicket.category.eventCategoryId)) {
            items[localTicket.category.eventCategoryId]?.add(localTicket)
        } else {
            items[localTicket.category.eventCategoryId] = mutableListOf(localTicket)
        }
    }

    fun remove(item: LocalTicket) {
        items.remove(item.category.eventCategoryId)
    }

    fun isEmpty(): Boolean {
        return items.isEmpty()
    }
}


data class LocalTicket(
        val event: EventDTO,
        val occurrence: Occurrence,
        val category: PriceCategory,
        val row: Row? = null,
        val seat: Seat? = null
)

data class TicketPayload(
        val amount: Int,
        val bookingInfo: BookingInfo,
        val rowseats: Array<RowSeat>
)

data class RowSeat(
        val rowID: Int,
        val seatID: Int
)

data class BookingResponse(
        val tranactionId: String?,
        val errMsg: String?
)

data class BookingInfo(
        val cardNr: Int,
        val city: String,
        val country: String,
        val house: String,
        val nameF: String,
        val nameL: String,
        val street: String,
        val zip: String
)

object OnPageAlert {
    fun showInfo(text: String) {
        val element = document.getElementById("alertBox")!!
        element.textContent = text
        element.removeClass("alertErr", "alertSuc", "none")
        element.addClass("alertInfo")
        addCloseBtn(element)
        window.setTimeout(handler = {
            element.addClass("none")
        }, timeout = 3000)
    }

    fun showErr(text: String) {
        val element = document.getElementById("alertBox")!!
        element.textContent = text
        element.removeClass("alertInfo", "alertSuc", "none")
        element.addClass("alertErr")
        addCloseBtn(element)
    }

    fun showSuc(text: String) {
        val element = document.getElementById("alertBox")!!
        element.textContent = text
        element.removeClass("alertInfo", "alertErr", "none")
        element.addClass("alertSuc")
        addCloseBtn(element)
        window.setTimeout(handler = {
            element.addClass("none")
        }, timeout = 3000)
    }

    private fun addCloseBtn(element: Element) {
        element.appendElement("span") {
            addClass("closebtn")
            addEventListener(type = "click", callback = {
                element.addClass("none")
            })
            textContent = "×"
        }
    }

    fun clear() {
        val element = document.getElementById("alertBox")!!
        element.textContent = ""
        element.addClass("none")
    }
}
