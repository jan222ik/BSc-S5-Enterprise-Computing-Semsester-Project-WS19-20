import org.w3c.dom.Element
import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLElement
import kotlin.browser.document
import kotlin.browser.window
import kotlin.dom.clear
import kotlin.dom.createElement
import kotlin.js.Promise

class Handler {
    val testURL: String = "http://localhost:8080"
    val searchURL: String = testURL + "/events/findByQueryString?queryString="


    fun getSearchResults(searchQuery: String): Promise<List<EventDTO>> {
        return window.fetch(searchURL + searchQuery).then { res ->
            return@then res.text()
        }.then {
            return@then JSON.parse<Array<EventDTO>>(it).toList()
        }.catch { trow ->
            console.log("Caught: $trow")
            return@catch emptyList<EventDTO>()
        }
    }
}

object DomModifier {
    fun populateContainer(eventDTOs: List<EventDTO>) {
        val container = document.getElementById("container") as HTMLElement
        container.clear()
        for (eventDTO in eventDTOs) {
            container.append(newEventDiv(eventDTO = eventDTO), document.createElement("hr"))
        }
    }

    private fun newEventDiv(eventDTO: EventDTO): Element {
        return document.createElement("div") {
            this.className = "event-element-conatiner"
            val titleL = label(text = "Title", cssExt = "title")
            val title = span(text = eventDTO.title, cssExt = "title")
            val genreL = label(text = "Genre", cssExt = "genre")
            val genre = span(text = eventDTO.genere, cssExt = "genre")
            val occL = label(text = "Occurrences", cssExt = "occurrences")
            val occContainer = document.createElement("div") {
                this.className = "occ-container"
                val table = document.createElement("table") {
                    this.className = "occ-table"

                    val header = document.createElement("thead") {
                        this.append(occHeader())
                    }
                    val occurrences = eventDTO.occurrences
                    val body = document.createElement("tbody")
                    for (i in occurrences.indices) {
                        console.dir(occurrences[i])
                        body.appendChild(occRow(eventDTO, occurrences[i]))
                    }
                    this.appendChild(header)
                    this.appendChild(body)
                }
                this.append(table)
            }
            this.append(titleL, title, br(), genreL, genre, br(), occL, br(), occContainer)
        }
    }

    private fun occHeader(): Element {
        val names = listOf("Date", "Time", "Location", "Tickets", "Type", "Price", "Action")
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
                    td(span("TODO", "occ-row-col-tickets")),
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
    }

    private fun td(child: Element): Element {
        return document.createElement("td") {
            this.append(child)
        }
    }

    private fun span(text: String, cssExt: String): Element {
        return document.createElement("span") {
            this.className = "event-element-$cssExt"
            this.textContent = text
        }
    }

    private fun label(text: String, cssExt: String): Element {
        return document.createElement("span") {
            this.className = "event-element-$cssExt-label"
            this.textContent = "$text: "
        }
    }

    private fun br(): Element {
        return document.createElement("br")
    }

    fun Occurrence.toLocationString() = "$country, $city"
    fun Date.toDateString() = "$year-$month-$dayOfMonth"
    fun Time.toTimeString() = "$hour:$minute"
}

fun main(args: Array<String>) {
    val searchButton = document.getElementById("searchBtn") as HTMLButtonElement
    searchButton.addEventListener(type = "click", callback = {
        val handler = Handler()
        val searchQuery = "-title=\"spon\""
        val searchQueryEmpty = ""
        val searchResults = handler.getSearchResults(searchQueryEmpty)
        searchResults.then {
            console.dir(it)
            DomModifier.populateContainer(it)
        }
    })
}

