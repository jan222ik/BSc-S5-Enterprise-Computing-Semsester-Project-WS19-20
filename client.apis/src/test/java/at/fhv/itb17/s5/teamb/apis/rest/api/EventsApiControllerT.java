package at.fhv.itb17.s5.teamb.apis.rest.api;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

class EventsApiControllerT {

    private static final String baseurl = "http://localhost:8080";
    private static final String urlBooking = baseurl + "/events/{eventID}/occurrences/{occID}/categories/{catID}/book";
    public static final String urlSearch = baseurl + "/events/findByQueryString";


    @Test
    void bookTicket200() {
        eventsFindByQueryStringGet200();
        given().pathParam("eventID", 28)
                .pathParam("occID", 32)
                .pathParam("catID", 34)
                .body("{\n" +
                        "  \"amount\": 10,\n" +
                        "  \"bookingInfo\": {\n" +
                        "    \"cardNr\": 0,\n" +
                        "    \"city\": \"string\",\n" +
                        "    \"country\": \"string\",\n" +
                        "    \"house\": \"string\",\n" +
                        "    \"nameF\": \"string\",\n" +
                        "    \"nameL\": \"string\",\n" +
                        "    \"street\": \"string\",\n" +
                        "    \"zip\": \"string\"\n" +
                        "  },\n" +
                        "  \"rowseats\": []\n" +
                        "}")
                .header("Content-Type", "application/json")
                .post(urlBooking)
                .then()
                .statusCode(200);
    }

    @Test
    void eventsFindByQueryStringGet200() {
        given().formParam("queryString", "-title=\"Sponsion\"")
                .get(urlSearch)
                .then()
                .statusCode(200);
    }

    @Test
    void eventsFindByQueryStringGet204() {
        given().formParam("queryString", "-title=\"Sponsionhuaehjofahiefjiawijdfaeijofijo\"")
                .get(urlSearch)
                .then()
                .statusCode(204);
    }

    @Test
    void eventsFindByQueryStringGet400() {
        given().formParam("queryString", "-titleX=\"Sponsion\"")
                .get(urlSearch)
                .then()
                .statusCode(400);
    }
}
