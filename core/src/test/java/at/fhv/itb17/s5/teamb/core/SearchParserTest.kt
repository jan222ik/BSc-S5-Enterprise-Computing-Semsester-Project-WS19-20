package at.fhv.itb17.s5.teamb.core

import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.fail
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

internal class SearchParserTest {

    private val now: String = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
    private val in7: String = LocalDate.now().plusDays(7L).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
    private val evtName: String = "A fancy concert"
    private val validString: String = "-until=\"$now\" -from=\"$in7\" -event=\"$evtName\""
    private val validSingle: String = "-event=\"$evtName\""

    @Test
    fun `Parsing Search Successful`() {
        val searchPairs: LinkedList<SearchParser.Search.SearchPair> = SearchParser.parseString(validString).retrieveSearchPairs()
        //searchPairs.forEach { p -> println("Key: ${p.key} Value: ${p.value}") }
        MatcherAssert.assertThat(searchPairs.size, Matchers.equalTo(3))
        MatcherAssert.assertThat(searchPairs[0].key, Matchers.equalTo(SearchParser.SearchCategories.DATE_UNTIL))
        MatcherAssert.assertThat(searchPairs[0].value, Matchers.equalTo(now))
        MatcherAssert.assertThat(searchPairs[1].key, Matchers.equalTo(SearchParser.SearchCategories.DATE_FROM))
        MatcherAssert.assertThat(searchPairs[1].value, Matchers.equalTo(in7))
        MatcherAssert.assertThat(searchPairs[2].key, Matchers.equalTo(SearchParser.SearchCategories.EVENT_NAME))
        MatcherAssert.assertThat(searchPairs[2].value, Matchers.equalTo(evtName))
    }

    @Test
    fun `Parsing Search Cached`() {
        val search = SearchParser.parseString(validSingle)
        val searchPairs: LinkedList<SearchParser.Search.SearchPair> = search.retrieveSearchPairs()
        MatcherAssert.assertThat(searchPairs[0].key, Matchers.equalTo(SearchParser.SearchCategories.EVENT_NAME))
        MatcherAssert.assertThat(searchPairs[0].value, Matchers.equalTo(evtName))
        val searchPairsCached: LinkedList<SearchParser.Search.SearchPair> = search.retrieveSearchPairs()
        MatcherAssert.assertThat(searchPairs, Matchers.equalTo(searchPairsCached))

    }

    @Test
    fun `Parsing Search Invalid String`() {
        val missingDash = "name='value'"
        var executed = true;
        try {
            SearchParser.parseString(missingDash)
            fail("Call should have thrown error");
        } catch (e: SearchParser.SearchException) {
            executed = false;
        }
        MatcherAssert.assertThat(executed, Matchers.`is`(false))
    }
}
