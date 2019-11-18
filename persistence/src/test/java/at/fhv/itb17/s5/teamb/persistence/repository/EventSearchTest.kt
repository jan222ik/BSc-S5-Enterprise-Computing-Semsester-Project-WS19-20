package at.fhv.itb17.s5.teamb.persistence.repository

import at.fhv.itb17.s5.teamb.persistence.entities.Event
import at.fhv.itb17.s5.teamb.persistence.provider.EventProvider
import at.fhv.itb17.s5.teamb.persistence.search.SearchCategories
import at.fhv.itb17.s5.teamb.persistence.search.SearchPair
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class EventSearchTest {
    private var entityRepository: EntityRepository = EntityRepository()
    private var eventRepository: EventRepository = EventRepository(entityRepository)
    private lateinit var e1: Event
    private lateinit var e2: Event
    private lateinit var e3: Event
    private lateinit var e4: Event
    private lateinit var e5: Event
    private lateinit var INVALID: Event
    private lateinit var allEvents: List<Event>


    init {
        e1 = EventProvider.getNewEventAndAddDB(entityRepository, "e1")
        e2 = EventProvider.getNewEventAndAddDB(entityRepository, "e2")
        e3 = EventProvider.getNewEventAndAddDB(entityRepository, "e3")
        e4 = EventProvider.getNewEventAndAddDB(entityRepository, "e4")
        e5 = EventProvider.getNewEventAndAddDB(entityRepository, "e5")
        INVALID = EventProvider.getNewTransientEvent("invalid")
        allEvents = LinkedList(listOf(e1, e2, e3, e4, e5))
    }

    @Test
    fun `Search Tickets - Success - Find All`() {
        val search = eventRepository.search(listOf())
        search.forEach {
            assertTrue(e1.eventId == it.eventId || e2.eventId == it.eventId || e3.eventId == it.eventId || e4.eventId == it.eventId || e5.eventId == it.eventId)
        }
    }

    @Test
    fun `Search Tickets - Success - by event title`() {
        val search = eventRepository.search(listOf(SearchPair(SearchCategories.EVENT_NAME, e1.title)))
        assertThat(search.size, Matchers.`is`(1))
        search.forEach {
            assertTrue(e1.eventId == it.eventId)
        }
    }

    @Test
    fun `Search Tickets - Fail - by event title`() {
        val search = eventRepository.search(listOf(SearchPair(SearchCategories.EVENT_NAME, INVALID.title)))
        assertThat(search.size, Matchers.`is`(0))
    }

    @Test
    fun `Search Tickets - Success - by event genre`() {
        val search = eventRepository.search(listOf(SearchPair(SearchCategories.GENRE, e1.genre)))
        assertThat(search.size, Matchers.`is`(1))
        search.forEach {
            assertTrue(e1.genre == it.genre)
        }
    }

    @Test
    fun `Search Tickets - Fail - by event genre`() {
        val search = eventRepository.search(listOf(SearchPair(SearchCategories.EVENT_NAME, INVALID.genre)))
        assertThat(search.size, Matchers.`is`(0))
    }

    @Test
    fun `Search Tickets - Success - by occurrence from date`() {
        TODO("Missing Repo Impl")
    }

    @Test
    fun `Search Tickets - Fail - by occurrence from date`() {
        TODO("Missing Repo Impl")
        val search = eventRepository.search(listOf(SearchPair(SearchCategories.DATE_FROM, LocalDate.MAX.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")))))
        assertThat(search.size, Matchers.`is`(0))
    }

    @Test
    fun `Search Tickets - Success - by occurrence until date`() {
        TODO("Missing Repo Impl")
    }

    @Test
    fun `Search Tickets - Fail - by occurrence until date`() {
        TODO("Missing Repo Impl")
        val search = eventRepository.search(listOf(SearchPair(SearchCategories.DATE_UNTIL, LocalDate.MIN.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")))))
        assertThat(search.size, Matchers.`is`(0))
    }

    @Test
    fun `Search Tickets - Success - by location`() {
        TODO("Missing Repo Impl")
    }

    @Test
    fun `Search Tickets - Fail - by location`() {
        TODO("Missing Repo Impl")
        val search = eventRepository.search(listOf(SearchPair(SearchCategories.LOCATION, INVALID.occurrences[0].address.city)))
        assertThat(search.size, Matchers.`is`(0))
    }


}
