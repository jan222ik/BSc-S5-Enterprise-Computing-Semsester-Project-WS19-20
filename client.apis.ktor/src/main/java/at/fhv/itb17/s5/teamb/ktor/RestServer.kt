package at.fhv.itb17.s5.teamb.ktor

import at.fhv.itb17.s5.teamb.core.domain.general.CoreServiceInjector
import at.fhv.itb17.s5.teamb.ktor.api.EventsApi
import at.fhv.itb17.s5.teamb.ktor.api.EventsApiController
import at.fhv.itb17.s5.teamb.ktor.model.TicketOrder
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receiveText
import io.ktor.response.respond
import io.ktor.response.respondFile
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.netty.NettyApplicationEngine
import java.io.*

class RestServer {


    @Throws(IOException::class)
    fun getResourceFiles(path: String): List<String> = getResourceAsStream(path).use{
        return if(it == null) emptyList()
        else BufferedReader(InputStreamReader(it)).readLines()
    }

    private fun getResourceAsStream(resource: String): InputStream? =
            Thread.currentThread().contextClassLoader.getResourceAsStream(resource)
                    ?: resource::class.java.getResourceAsStream(resource)

        fun main(injector: CoreServiceInjector) {
            getResourceFiles("/").forEach{str ->
                println(str)}
            val api: EventsApi = EventsApiController(injector)
            val mapper = jacksonObjectMapper()
            mapper.registerModule(KotlinModule())
            val server = embeddedServer(Netty, 8082) {
                routing {
                    // STATIC CONTENT SERVE
                    get(path = "/") {
                        call.respondFile(file = File("Z:\\Users\\jan22\\CodeProjects\\advance-ticket-sale\\kotlinweb\\src\\main\\resources\\TestJS.html"))
                    }
                    get(path = "/lib/kotlin.js") {
                        val resource = javaClass.getResource("/kotlin.js")
                        call.respondFile(file = File(resource.path))
                    }
                    get(path = "/lib/webjs.js") {
                        call.respondFile(file = File("Z:\\Users\\jan22\\CodeProjects\\advance-ticket-sale\\kotlinweb\\build\\classes\\kotlin\\main\\kotlinweb.js"))
                    }
                    // REST API
                    @Suppress("BlockingMethodInNonBlockingContext")
                    post(path = "/events/{eventID}/occurrences/{occID}/categories/{catID}/book") {
                        val eventID = call.parameters["eventID"]!!.toLong()
                        val occID = call.parameters["occID"]!!.toLong()
                        val catID = call.parameters["catID"]!!.toLong()
                        val body = mapper.readValue<TicketOrder>(call.receiveText(), TicketOrder::class.java)
                        val result = api.bookTicket(eventID, occID, catID, body)
                        println(result.generic)
                        val status = HttpStatusCode.fromValue( value = result.status)
                        if (result.generic != null) {
                            call.respondText(text =  mapper.writeValueAsString(result.generic), status = status)
                        } else {
                            call.respond(status = status, message = status.description)
                        }
                    }
                    @Suppress("BlockingMethodInNonBlockingContext")
                    get(path = "/events/findByQueryString") {
                        val searchString: String = this.context.request.queryParameters["queryString"] ?: ""
                        println("searchString = $searchString")
                        val result = api.eventsFindByQueryStringGet(searchString)
                        val status = HttpStatusCode.fromValue(value = result.status)
                        if (result.generic != null) {
                            call.respondText(text =  mapper.writeValueAsString(result.generic), status = status)
                        } else {
                            call.respond(status = status, message = status.description)
                        }
                    }
                }
            }
            server.start(wait = true)
        }


        private fun html(): String = """
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>
        tbody {
            display: block;
            max-height: 500px;
            overflow-y: scroll;
        }

        thead,
        tbody tr {
            display: table;
            width: 100%;
        }

        thead {
            width: 100%;
        }

        table {
            display: table;
            width: 700px;
        }


        /* Style the tab */
        .tab {
            overflow: hidden;
            border: 1px solid #ccc;
            background-color: #f1f1f1;
        }

        /* Style the buttons that are used to open the tab content */
        .tab button {
            background-color: inherit;
            float: left;
            border: none;
            outline: none;
            cursor: pointer;
            padding: 14px 16px;
            transition: 0.3s;
        }

        /* Change background color of buttons on hover */
        .tab button:hover {
            background-color: #ddd;
        }

        /* Create an active/current tablink class */
        .tab button.active {
            background-color: #ccc;
        }

        /* Style the tab content */
        .tabcontent {
            padding: 6px 12px;
            border: 1px solid #ccc;
            border-top: none;
        }

        .none {
            display: none;
        }
        /* The alert message box */
        .alertErr {
            padding: 20px;
            background-color: #f44336;
            color: white;
            margin-bottom: 15px;
        }

        .alertSuc {
            padding: 20px;
            background-color: #6bbd6e;
            color: white;
            margin-bottom: 15px;
        }

        .alertInfo {
            padding: 20px;
            background-color: #0097f4;
            color: white;
            margin-bottom: 15px;
        }

        /* The close button */
        .closebtn {
            margin-left: 15px;
            color: white;
            font-weight: bold;
            float: right;
            font-size: 22px;
            line-height: 20px;
            cursor: pointer;
            transition: 0.3s;
        }

        /* When moving the mouse over the close button */
        .closebtn:hover {
            color: black;
        }

    </style>
</head>
<body>
<div id="alertBox" class="alertInfo none"></div>
<div class="tab">
    <button id="searchTabBtn" class="tablinks">Search</button>
    <button id="containerBtn" class="tablinks">Browser</button>
    <button id="cartBtn" class="tablinks">Cart</button>
</div>
<div id="container" class="tabcontent"></div>
<div id="cart" class="tabcontent"></div>
<div id="search" class="tabcontent">
    <div class="center">
        <ul>
            <label class="label"><b>Name of Event:</b></label>
            <input id="event-title" type="text" name="EventName">
            <br>
            <br>
            <label class="label"><b>Date:</b></label>
            <label>from:</label>
            <input id="withFrom" type="checkbox">
            <input id="fromDate" type="date" name="date">
            <br>
            <label>to:</label>
            <input id="withTill" type="checkbox">
            <input id="tillDate" type="date" name="date">
            <br>
            <br>
            <label class="label"><b>Genre:</b></label>
            <input id="genre" type="text" name="Genre">
            <br>
            <br>
            <label class="label"><b>Artist:</b></label>
            <input id="artist" type="text" name="Artist">
            <br>
            <br>
            <label class="label"><b>Location:</b></label>
            <input id="location" type="text" name="Location">
            <br>
            <br>
            <button id="resetBtn" name="Reset Filter" value="Reset Filter">Reset</button>
            <button id="searchBtn">Search</button>
        </ul>
    </div>
</div>
<div id="paymentInfo" class="tabcontent">
    <div class="center">
        <ul>
            <label class="label"><b>Card Number:</b></label>
            <input id="cardNumber" type="text" name="cardNr">
            <br>
            <br>
            <label class="label"><b>City:</b></label>
            <input id="city" type="text" name="City">
            <br>
            <br>
            <label class="label"><b>Country:</b></label>
            <input id="country" type="text" name="Country">
            <br>
            <br>
            <label class="label"><b>House:</b></label>
            <input id="house" type="text" name="House">
            <br>
            <br>
            <label class="label"><b>First Name:</b></label>
            <input id="nameF" type="text" name="NameF">
            <br>
            <br>
            <label class="label"><b>Last Name:</b></label>
            <input id="nameL" type="text" name="NameL">
            <br>
            <br>
            <label class="label"><b>Street:</b></label>
            <input id="street" type="text" name="Street">
            <br>
            <br>
            <label class="label"><b>Zip Code:</b></label>
            <input id="zip" type="text" name="Zip">
            <br>
            <br>
            <button id="confirmBtn">Confirm Payment</button>
        </ul>
    </div>
</div>
<script src="/lib/kotlin.js"></script>
<script src="/lib/webjs.js"></script>
</body>
</html>
    """.trimIndent()
}
