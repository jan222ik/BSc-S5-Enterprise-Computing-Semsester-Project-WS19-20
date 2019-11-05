package at.fhv.itb17.s5.teamb.core.domain.search

import at.fhv.itb17.s5.teamb.persistence.search.Search

object SearchParser {

    fun parseString(queryString: String): Search {
        return Search(queryString)
    }
}
