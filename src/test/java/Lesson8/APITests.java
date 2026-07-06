
package Lesson8;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class APITests {

    private static final String BASE_URL = "https://postman-echo.com";

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void getRequestTest() {
        given()
                .queryParam("foo1", "bar1")
                .queryParam("foo2", "bar2")
                .log().body()
                .when()
                .get("/get")
                .then()
                .log().body()
                .statusCode(200)
                .body("args.foo1", equalTo("bar1"))
                .body("args.foo2", equalTo("bar2"))
                .body("url", containsString("/get"));
    }

    @Test
    public void postRequestTest() {
        Map<String, Object> person = new HashMap<>();
        person.put("name", "John Doe");
        person.put("age", 30);

        given()
                .log().body()
                .contentType("application/json")
                .body(person)
                .when()
                .post("/post")
                .then()
                .log().body()
                .statusCode(200)
                .body("json.name", equalTo("John Doe"))
                .body("json.age", equalTo(30))
                .body("url", equalTo(BASE_URL + "/post"));
    }



    @Test
    public void putRequestTest() {
        Map<String, Object> data = new HashMap<>();
        data.put("updated", "data");
        data.put("version", 2);

        given()
                .log().body()
                .contentType("application/json")
                .body(data)
                .when()
                .put("/put")
                .then()
                .log().body()
                .statusCode(200)
                .body("json.updated", equalTo("data"))
                .body("json.version", equalTo(2))
                .body("url", equalTo(BASE_URL + "/put"));
    }

    @Test
    public void patchRequestTest() {
        Map<String, Object> data = new HashMap<>();
        data.put("patch", "data");
        data.put("version", 3);

        given()
                .log().body()
                .contentType("application/json")
                .body(data)
                .when()
                .patch("/patch")
                .then()
                .log().body()
                .statusCode(200)
                .body("json.patch", equalTo("data"))
                .body("json.version", equalTo(3))
                .body("url", equalTo(BASE_URL + "/patch"));
    }

    @Test
    public void deleteRequestTest() {
        given()
                .log().body()
                .queryParam("id", "123")
                .when()
                .delete("/delete")
                .then()
                .log().body()
                .statusCode(200)
                .body("args.id", equalTo("123"))
                .body("url", containsString("/delete"));
    }

    @Test
    public void headRequestTest() {
        given()
                .log().body()
                .when()
                .head("/get")
                .then()
                .log().body()
                .statusCode(200)
                .header("Content-Type", notNullValue())
                .header("Connection", notNullValue());
    }

    @Test
    public void optionsRequestTest() {
        given()
                .log().body()
                .when()
                .options("/get")
                .then()
                .log().body()
                .statusCode(200)
                .header("Allow", notNullValue())
                .header("Content-Type", notNullValue());
    }
}