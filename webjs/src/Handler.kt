import kotlin.browser.window

class Handler {
    val testURL: String = "http://localhost:8080"
    val searchURL: String = testURL + "/events/findByQueryString?queryString="


    fun getSearchResults(): List<EventDTO> {
        val searchQuery: String = "-title=\"spon\""
        window.fetch(searchURL + searchQuery).then { res ->
            res.text().then { console.log(it) }
        }
        return emptyList()
    }
}

fun main(args: Array<String>) {
    val handler = Handler()
    handler.getSearchResults()
}

