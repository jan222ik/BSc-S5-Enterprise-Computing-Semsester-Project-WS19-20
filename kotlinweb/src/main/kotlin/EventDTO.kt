data class EventDTO(
    val organizerId: Int,
    val org_name: String,
    val org_email: String,
    val org_addressId: Int,
    val org_country: String,
    val org_zip: String,
    val org_city: String,
    val org_street: String,
    val org_house: String,
    val eventId: Int,
    val title: String,
    val description: String,
    val genere: String,
    val artistNames: Array<String>,
    val occurrences: Array<Occurrence>
)

data class Occurrence(
    val occurrenceId: Int,
    val date: Date,
    val time: Time,
    val priceCategories: Array<PriceCategory>,
    val addressId: Int,
    val country: String,
    val zip: String,
    val city: String,
    val street: String,
    val house: String,
    val categoryCalcDataDTO: CategoryCalcDataDTO
)

data class Date(
    val year: Int,
    val month: String,
    val dayOfMonth: Int,
    val monthValue: Int,
    val chronology: Chronology,
    val dayOfWeek: String,
    val era: String,
    val dayOfYear: Int,
    val leapYear: Boolean
)

data class Chronology(
    val id: String,
    val calendarType: String
)

data class Time(
    val hour: Int,
    val minute: Int,
    val second: Int,
    val nano: Int
)

data class PriceCategory(
    val type: String,
    val eventCategoryId: Int,
    val categoryName: String,
    val priceInCents: Int,
    val totalTickets: Int,
    val usedTickets: Int,
    val rows: Array<Row>
)

data class Row(
        val rowId: Int,
        val rowIdf: String,
        val seats: Array<Seat>
)

data class Seat(
        val free: Boolean,
        val seatId: Int,
        val isFree: Boolean,
        val seatIdf: String
)

data class CategoryCalcDataDTO(
    val priceRangeString: String,
    val minPrice: Int,
    val maxPrice: Int,
    val ticketTypes: String
)
