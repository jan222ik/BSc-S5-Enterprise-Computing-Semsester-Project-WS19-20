package at.fhv.itb17.s5.teamb.persistence.search

import java.util.*
import kotlin.collections.HashMap

class Search(private val queryString: String) {

    init {
        if (!queryString.matches("""-[^-="]*="[^"]*"( *-[^-="]*="[^"]*")*""".toRegex())) {
            throw SearchException("String in wrong format")
        }
    }

    private var cache: LinkedList<SearchPair>? = null;
    fun retrieveSearchPairs(): LinkedList<SearchPair> {
        return cache ?: queryString
                .split('-').drop(1) //Drop first empty element
                .map { s: String ->
                    SearchPair(
                            SearchCategories.matchCat(s.substringBefore('=')),
                            s.substringAfter('"').substringBefore('"')
                    )
                }
                .toCollection(LinkedList())
                .also { cache = it }
    }

}

data class SearchPair(
        val key: SearchCategories,
        val value: String
)

enum class SearchCategories(private val catIdf: String) {
    DATE_FROM("from"), DATE_UNTIL("until"),
    EVENT_NAME("event"), ARTIST_NAME("artist"),
    GENRE("genre"), LOCATION("location");

    companion object {
        private var map: HashMap<String, SearchCategories> = HashMap()

        init {
            values().forEach { map[it.catIdf] = it }
        }

        fun matchCat(s: String): SearchCategories {
            return map[s] ?: throw SearchException("Invalid type identifier: $s");
        }
    }
}

class SearchException(msg: String) : Exception(msg)
