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
    private val client = Client("test_client", "Hugo Hugo", ClientRoles.EXTERNAL, address)
    private val g21 = EventCategory("G21", 9001, 500, 69)
    private val g16 = EventCategory("G16", 12312312, 19, 3)
    private val occurrence0 = EventOccurrence(LocalDate.now(), LocalTime.now(), LinkedList(Arrays.asList(g21, g16)), address)
    private val organizer = Organizer("Std. Do", "e@mail.com", address)
    private val hugo_hugo = Artist("Hugo Hugo")
    private val artists = LinkedList(Arrays.asList(hugo_hugo))
    private val event = Event("Weihnachtsmarkt", "Weihnachtsmarkt vom 22.11 bis 23.11.2019", "Death Metal", LinkedList(Arrays.asList(occurrence0)), organizer, artists)


    @Test
    fun `Insert single ticket`() {
        entityRepository.save(event)
        val aNewTicket = getANewTicket()
        assertThat(aNewTicket.ticketId, Matchers.nullValue())
        ticketRepository.bookIfFree(aNewTicket)
        assertThat(aNewTicket.ticketId, Matchers.notNullValue())
    }

    @Test
    fun `Insert fails if occ does not exist`() {
        val aNewTicket = getANewTicket()
        assertThat(aNewTicket.ticketId, Matchers.nullValue())
        ticketRepository.bookIfFree(aNewTicket)
        assertThat(aNewTicket.ticketId, Matchers.nullValue())
    }


    fun getANewTicket(): Ticket {
        return Ticket(client, TicketStates.PAID, event, occurrence0, g21, null, null)
    }

    @Test
    fun `Book or Reserve List of Tickets - Success`() {
        TODO("Implement Test")
    }

    @Test
    fun `Book or Reserve Tickets - Fail - OneOf tickets already exists`() {
        TODO("Implement Test")
    }

    @Test
    fun `Book or Reserve Tickets - Fail - Seat for Occurrence already booked`() {
        TODO("Implement Test")
    }

    @Test
    fun `Book or Reserve Tickets - Fail - Number of Seats is insufficient`() {
        TODO("Implement Test")
    }

    @Test
    fun `Book or Reserve Tickets - Fail - Number of Free Seats is insufficent`() {
        TODO("Implement Test")
    }

}
