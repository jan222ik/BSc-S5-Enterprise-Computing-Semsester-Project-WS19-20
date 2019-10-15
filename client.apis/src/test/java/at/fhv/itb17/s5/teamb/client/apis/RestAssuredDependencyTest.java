package at.fhv.itb17.s5.teamb.client.apis;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@SuppressWarnings("WeakerAccess")
public class RestAssuredDependencyTest {

    @Test
    public void canStartRestAssured() {
        given().get("http://www.google.com").then().statusCode(is(200));
    }
}
