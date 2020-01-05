package at.fhv.itb17.s5.teamb.core

import at.fhv.itb17.s5.teamb.core.domain.general.CoreServiceInjectorImpl
import at.fhv.itb17.s5.teamb.persistence.entities.Event
import at.fhv.itb17.s5.teamb.persistence.search.SearchCategories
import at.fhv.itb17.s5.teamb.persistence.search.SearchPair
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.stream.Collectors

class SearchServiceCoreTest {

    private val now: String = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
    private val in7: String = LocalDate.now().plusDays(7L).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
    private val evtName: String = "A fancy concert"
    private val validString: String = "-until=\"$now\" -from=\"$in7\" -title=\"$evtName\""
    private val validSingle: String = "-title=\"$evtName\""

    private fun toSearchString(cats: List<SearchPair>): String {
        return cats.stream().map { e -> "-" + e.key.getIdf() + "=\"" + e.value.substringBefore('-') + "\"" }.collect(Collectors.joining(" "))
    }

    @Test
    fun `Search Tickets - Success - Find All`() {
        val coreServiceInjectorImpl = CoreServiceInjectorImpl.getInstance(false)
        val search = coreServiceInjectorImpl.searchServiceCore.searchFor("")
        assertThat(search.size, `is`(6))
    }

    @Test
    fun `Search Tickets - Success - by event title`() {
        val coreServiceInjectorImpl = CoreServiceInjectorImpl.getInstance(false)
        val evt = coreServiceInjectorImpl.entityRepository.getAll(Event::class.java, listOf())[0]
        val pair = toSearchString(listOf(SearchPair(SearchCategories.EVENT_NAME, evt.title)))
        val search = coreServiceInjectorImpl.searchServiceCore.searchFor(pair)
        assertThat(search.size, Matchers.`is`(2))
        val anyMatch = search.stream().anyMatch { it.eventId == evt.eventId }
        assertTrue(anyMatch)
    }

    @Test
    fun `Search Tickets - Success - by artist name`() {
        val coreServiceInjectorImpl = CoreServiceInjectorImpl.getInstance(false)
        val evt = coreServiceInjectorImpl.entityRepository.getAll(Event::class.java, listOf())[0]
        val pair = toSearchString(listOf(SearchPair(SearchCategories.ARTIST_NAME, evt.artists[0].name)))
        val search = coreServiceInjectorImpl.searchServiceCore.searchFor(pair)
        assertThat(search.size, Matchers.`is`(1))
        val anyMatch = search.stream().anyMatch { it.eventId == evt.eventId }
        assertTrue(anyMatch)
    }

    @Test
    fun `Search Tickets - Fail - by event title`() {
        val coreServiceInjectorImpl = CoreServiceInjectorImpl.getInstance(false)
        val pair = toSearchString(listOf(SearchPair(SearchCategories.EVENT_NAME, "DOESNOTEXIST")))
        println("pair = ${pair}")
        val search = coreServiceInjectorImpl.searchServiceCore.searchFor(pair)
        assertThat(search.size, Matchers.`is`(0))
    }

    @Test
    fun `Search Tickets - Success - by event genre`() {
        val coreServiceInjectorImpl = CoreServiceInjectorImpl.getInstance(false)
        val evt = coreServiceInjectorImpl.entityRepository.getAll(Event::class.java, listOf())[4]
        val pair = toSearchString(listOf(SearchPair(SearchCategories.GENRE, evt.genre)))
        val search = coreServiceInjectorImpl.searchServiceCore.searchFor(pair)
        assertThat(search.size, Matchers.`is`(3))
        val anyMatch = search.stream().allMatch {
            it.genre == evt.genre
        }
        assertTrue(anyMatch)
    }

    @Test
    fun `Search Tickets - Fail - by event genre`() {
        val coreServiceInjectorImpl = CoreServiceInjectorImpl.getInstance(false)
        val pair = toSearchString(listOf(SearchPair(SearchCategories.GENRE, "DOESNOTEXIST")))
        val search = coreServiceInjectorImpl.searchServiceCore.searchFor(pair)
        assertThat(search.size, Matchers.`is`(0))
    }

    @Test
    fun `Search Tickets - Success - by date from and genre`() {
        val coreServiceInjectorImpl = CoreServiceInjectorImpl.getInstance(false)
        val evt = coreServiceInjectorImpl.entityRepository.getAll(Event::class.java, listOf())[4]
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        val pair = toSearchString(listOf(SearchPair(SearchCategories.GENRE, evt.genre), SearchPair(SearchCategories.DATE_FROM, evt.occurrences[0].date.minusDays(1).format(formatter))))
        val search = coreServiceInjectorImpl.searchServiceCore.searchFor(pair)
        assertThat(search.size, Matchers.`is`(1))
        val anyMatch = search.stream().allMatch {
            it.genre == evt.genre
        }
        assertTrue(anyMatch)
    }

    @Test
    fun `Search Tickets - Success - by date until and genre`() {
        val coreServiceInjectorImpl = CoreServiceInjectorImpl.getInstance(false)
        val evt = coreServiceInjectorImpl.entityRepository.getAll(Event::class.java, listOf())[4]
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        val pair = toSearchString(listOf(SearchPair(SearchCategories.GENRE, evt.genre), SearchPair(SearchCategories.DATE_UNTIL, evt.occurrences.get(0).date.plusDays(1L).format(formatter))))
        val search = coreServiceInjectorImpl.searchServiceCore.searchFor(pair)
        assertThat(search.size, Matchers.`is`(3))
        val anyMatch = search.stream().allMatch {
            it.genre == evt.genre
        }
        assertTrue(anyMatch)
    }

    @Test
    fun `Search Tickets - Success - by location`() {
        val coreServiceInjectorImpl = CoreServiceInjectorImpl.getInstance(false)
        val evt = coreServiceInjectorImpl.entityRepository.getAll(Event::class.java, listOf())[4]
        val pair = toSearchString(listOf(SearchPair(SearchCategories.LOCATION, evt.occurrences[0].address.city)))
        val search = coreServiceInjectorImpl.searchServiceCore.searchFor(pair)
        assertThat(search.size, Matchers.`is`(2))
        val anyMatch = search.stream().allMatch {
            it.occurrences[0].address.city == evt.occurrences[0].address.city
        }
        assertTrue(anyMatch)
    }
}
