using NUnit.Framework;
using RA;

namespace client.test
{
    [TestFixture]
    public class UnitTest1
    {  
    private static string baseurl = "http://localhost:8082";
    private static string urlBooking = baseurl + "/events/{eventID}/occurrences/{occID}/categories/{catID}/book";
    public static string urlSearch = baseurl + "/events/findByQueryString";


    [Test]
    public void bookTicket200() {
        eventsFindByQueryStringGet200();
        new RestAssured().Given().Query("eventID", "28")
                .Query("occID", "32")
                .Query("catID", "34")
                .Body("{\n" +
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
                .Header("Content-Type", "application/json").When().Post(urlBooking).Then().TestStatus("test by query string 200", x => x == 200);
    }

    [Test]
    public void eventsFindByQueryStringGet200() {
       new RestAssured().Given().Query("queryString", "-title=\"Sponsion\"")
                .When().Get(urlSearch).Then().TestStatus("test 200", x => x == 200);
    }

    [Test]
    public void eventsFindByQueryStringGet204() {
        new RestAssured().Given().Query("queryString", "-title=\"Sponsionhuaehjofahiefjiawijdfaeijofijo\"")
                .When().Get(urlSearch)
                .Then()
                .TestStatus("test query string 204", x => x == 204);
    }

    [Test]
    public void eventsFindByQueryStringGet400() {
        new RestAssured().Given().Query("queryString", "-titleX=\"Sponsion\"")
                .When().Get(urlSearch)
                .Then()
                .TestStatus("test query string 400", x => x == 400);
    }
    }
}