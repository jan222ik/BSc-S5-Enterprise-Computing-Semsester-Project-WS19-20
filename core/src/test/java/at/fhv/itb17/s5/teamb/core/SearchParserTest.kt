package at.fhv.itb17.s5.teamb.core

import at.fhv.itb17.s5.teamb.core.domain.search.SearchParser
import at.fhv.itb17.s5.teamb.persistence.search.SearchCategories
import at.fhv.itb17.s5.teamb.persistence.search.SearchException
import at.fhv.itb17.s5.teamb.persistence.search.SearchPair
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

internal class SearchParserTest {

    private val now: String = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
    private val in7: String = LocalDate.now().plusDays(7L).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
    private val evtName: String = "A fancy concert"
    private val validString: String = "-until=\"$now\" -from=\"$in7\" -title=\"$evtName\""
    private val validSingle: String = "-title=\"$evtName\""

    @Test
    fun `Parsing Search Successful`() {
        val searchPairs: LinkedList<SearchPair> = SearchParser.parseString(validString)!!.retrieveSearchPairs()
        //searchPairs.forEach { p -> println("Key: ${p.key} Value: ${p.value}") }
        MatcherAssert.assertThat(searchPairs.size, Matchers.equalTo(3))
        MatcherAssert.assertThat(searchPairs[0].key, Matchers.equalTo(SearchCategories.DATE_UNTIL))
        MatcherAssert.assertThat(searchPairs[0].value, Matchers.equalTo(now))
        MatcherAssert.assertThat(searchPairs[1].key, Matchers.equalTo(SearchCategories.DATE_FROM))
        MatcherAssert.assertThat(searchPairs[1].value, Matchers.equalTo(in7))
        MatcherAssert.assertThat(searchPairs[2].key, Matchers.equalTo(SearchCategories.EVENT_NAME))
        MatcherAssert.assertThat(searchPairs[2].value, Matchers.equalTo(evtName))
    }

    @Test
    fun `Parsing Search Cached`() {
        val search = SearchParser.parseString(validSingle)
        val searchPairs: LinkedList<SearchPair> = search!!.retrieveSearchPairs()
        MatcherAssert.assertThat(searchPairs[0].key, Matchers.equalTo(SearchCategories.EVENT_NAME))
        MatcherAssert.assertThat(searchPairs[0].value, Matchers.equalTo(evtName))
        val searchPairsCached: LinkedList<SearchPair> = search.retrieveSearchPairs()
        MatcherAssert.assertThat(searchPairs, Matchers.equalTo(searchPairsCached))

    }

    @Test
    fun `Parsing Search Invalid String`() {
        val invalid = "name='value'"
        @Suppress("VARIABLE_WITH_REDUNDANT_INITIALIZER")
        var executed = true
        try {
            SearchParser.parseString(invalid)
            fail("Call should have thrown error")
        } catch (e: SearchException) {
            executed = false
        }
        MatcherAssert.assertThat(executed, Matchers.`is`(false))
    }
}
