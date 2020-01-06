package at.fhv.itb17.s5.teamb.persistence.repository

import at.fhv.itb17.s5.teamb.persistence.entities.Event
import at.fhv.itb17.s5.teamb.persistence.provider.EventProvider
import at.fhv.itb17.s5.teamb.persistence.search.SearchCategories
import at.fhv.itb17.s5.teamb.persistence.search.SearchPair
import junit.framework.Assert.assertTrue
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import java.util.*

class EventSearchTest {

    private var entityRepository: EntityRepository = EntityRepository()
    private var eventRepository: EventRepository = EventRepository(entityRepository)
    private var e1: Event = EventProvider.getNewEventAndAddDB("e1")
    private var e2: Event = EventProvider.getNewEventAndAddDB("e2")
    private var e3: Event = EventProvider.getNewEventAndAddDB("e3")
    private var e4: Event = EventProvider.getNewEventAndAddDB("e4")
    private var e5: Event = EventProvider.getNewEventAndAddDB("e5")
    private var INVALID: Event = EventProvider.getNewTransientEvent("invalid")
    private var allEvents: List<Event> = LinkedList(listOf(e1, e2, e3, e4, e5))

    fun upsert(evts: List<Event>) {
        if(eventRepository.search(listOf()).size == 0) {
            var i : Long = 1
            evts.forEach { e -> entityRepository.saveOrUpdate(e) }
//            allEvents = eventRepository.search(listOf())
        }
        else{
            allEvents = eventRepository.search(listOf())
        }
    }

    @Test
    fun `Search Tickets - Success - Find All`() {
        upsert(allEvents)
        val search = eventRepository.search(listOf())
        search.forEach {
            assertTrue(e1.eventId == it.eventId || e2.eventId == it.eventId || e3.eventId == it.eventId || e4.eventId == it.eventId || e5.eventId == it.eventId)
        }
    }

    @Test
    fun `Search Tickets - Success - by event title`() {
        upsert(allEvents)
        val search = eventRepository.search(listOf(SearchPair(SearchCategories.EVENT_NAME, e1.title)))
        assertThat(search.size, Matchers.`is`(1))
        search.forEach {
            assertTrue(allEvents[0].eventId == it.eventId)
        }
    }

    @Test
    fun `Search Tickets - Fail - by event title`() {
        upsert(allEvents)
        val search = eventRepository.search(listOf(SearchPair(SearchCategories.EVENT_NAME, INVALID.title)))
        assertThat(search.size, Matchers.`is`(0))
    }

    @Test
    fun `Search Tickets - Success - by event genre`() {
        upsert(allEvents)
        val search = eventRepository.search(listOf(SearchPair(SearchCategories.GENRE, e1.genre)))
        assertThat(search.size, Matchers.`is`(1))
        search.forEach {
            assertTrue(e1.genre == it.genre)
        }
    }

    @Test
    fun `Search Tickets - Fail - by event genre`() {
        upsert(allEvents)
        val search = eventRepository.search(listOf(SearchPair(SearchCategories.EVENT_NAME, INVALID.genre)))
        assertThat(search.size, Matchers.`is`(0))
    }


    /*
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
    */

}
