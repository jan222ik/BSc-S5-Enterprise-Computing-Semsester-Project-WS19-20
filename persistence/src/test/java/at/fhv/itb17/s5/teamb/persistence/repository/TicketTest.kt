package at.fhv.itb17.s5.teamb.persistence.repository

import at.fhv.itb17.s5.teamb.persistence.entities.*
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalTime
import java.util.*


class TicketTest {

    private var entityRepository: EntityRepository = EntityRepository()
    private var ticketRepository: TicketRepository = TicketRepository(entityRepository)

    private val address = Address("AT", "685ftui0", "Do", "ABCStr.", "4711")
    private val client = Client("test_client", "Hugo Hugo", LinkedList(), LinkedList(), address)
    private val g21 = EventCategory("G21", 9001, 100, 69)
    private val seats = listOf(LocationSeat("1", false), LocationSeat("2", false), LocationSeat("3", true))
    private val rows = listOf(LocationRow("A", seats))
    private val g16 = EventCategory("G16", 12312312, rows)
    private val occurrence0 = EventOccurrence(LocalDate.now(), LocalTime.now(), LinkedList(listOf(g21, g16)), address)
    private val organizer = Organizer("Std. Do", "e@mail.com", address)
    private val hugo_hugo = Artist("Hugo Hugo")
    private val artists = LinkedList(listOf(hugo_hugo))
    private val event = Event("Weihnachtsmarkt", "Weihnachtsmarkt vom 22.11 bis 23.11.2019", "Death Metal", LinkedList(listOf(occurrence0)), organizer, artists)

    private fun getANewTicket(withSeats: Boolean = false, row: LocationRow? = null, seat: LocationSeat? = null): Ticket {
        return Ticket(client, TicketStates.PAID, event, occurrence0, if (withSeats) g16 else g21, row, seat)
    }

    @Test
    fun `Insert single ticket`() {
        entityRepository.save(event)
        assertThat(event.eventId, Matchers.notNullValue())
        val aNewTicket = getANewTicket()
        assertThat(aNewTicket.ticketId, Matchers.nullValue())
        val bookIfFree = ticketRepository.bookIfFree(aNewTicket)
        assertThat(bookIfFree, Matchers.notNullValue())
        assertThat(bookIfFree!!.ticketId, Matchers.notNullValue())
    }


    @Test
    fun `Insert fails if necessary booking info does not exist`() {
        val aNewTicket = getANewTicket()
        assertThat(aNewTicket.ticketId, Matchers.nullValue())
        ticketRepository.bookIfFree(aNewTicket)
        assertThat(aNewTicket.ticketId, Matchers.nullValue())
    }

    @Test
    fun `Book or Reserve Tickets - Fail - Ticket for seat already exists`() {
        entityRepository.save(event)
        val aNewTicket = getANewTicket(true, rows[0], seats[0])
        val aNewTicket2 = getANewTicket(true, rows[0], seats[0])
        val bookIfFree = ticketRepository.bookIfFree(aNewTicket)
        assertThat(bookIfFree?.ticketId, Matchers.notNullValue())
        assertThat(aNewTicket.ticketId, Matchers.notNullValue())
        val bookIfFree1 = ticketRepository.bookIfFree(aNewTicket2)
        assertThat(bookIfFree1, Matchers.nullValue())
        assertThat(aNewTicket2.ticketId, Matchers.nullValue())
    }

    @Test
    fun `Book or Reserve Tickets - Fail - Number of Free Seats is insufficient`() {
        entityRepository.save(event)
        val tickets = mutableListOf<Ticket>()
        for (i: Int in 0..g21.totalSpace + 1) {
            tickets.add(getANewTicket())
        }
        println(tickets.size)
        val bookIfFree1 = ticketRepository.bookIfFree(tickets)
        assertThat(bookIfFree1, Matchers.nullValue())
    }

    @Test
    fun `Book or Reserve Tickets - Fail - Specific Seats or Row null`() {
        entityRepository.save(event)
        val t1 = getANewTicket(true, null, null)
        val bookIfFree1 = ticketRepository.bookIfFree(t1)
        assertThat(bookIfFree1, Matchers.nullValue())
        val t2 = getANewTicket(true, rows[0], null)
        val bookIfFree2 = ticketRepository.bookIfFree(t2)
        assertThat(bookIfFree2, Matchers.nullValue())
        val t3 = getANewTicket(true, null, seats[0])
        val bookIfFree3 = ticketRepository.bookIfFree(t3)
        assertThat(bookIfFree3, Matchers.nullValue())
    }

}
