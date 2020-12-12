package restAPITudy;

// 3 static imports
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class GETRequests {

    /*
    Ping Postman

    Given
    When GET request
    Then return String
     */
    @Test
    public void pingRequest(){

        String str = given()
        .when()
                .get("http://localhost:8083/laptop-bag/webapi/api/ping/postman")
        .thenReturn()
                .asString();
        System.out.println(str);
    }

    /*
    Given I accept as JSON
    When I send a GET request
    Then return as Response
     */
    @Test
    public void getAllRecs(){
        Response response =
                given().accept(ContentType.JSON).when().get("http://localhost:8083/laptop-bag/webapi/api" +
                        "/all");
        System.out.println(response.asString()); //prettyPrint()
    }

    /*
    Given I accept as JSON
    When I send a GET request
    Then return as Response
     */
    @Test
    public void thenAndThenReturnMethods(){
        int statusCode = given().accept(ContentType.JSON).when().get("http://localhost:8083/laptop-bag/webapi/api" +
                "/all").thenReturn().statusCode();
        Assert.assertEquals(statusCode, 200);

        given().accept(ContentType.JSON).when().get("http://localhost:8083/laptop-bag/webapi/api" +
                "/all").then().statusCode(400);

        // Headers
        Map<String, String> map = new HashMap<>();
        map.put("Accept", "application/json");

        given().headers(map).when().get("http://localhost:8083/laptop-bag/webapi/api/all").then().statusCode(HttpStatus.SC_OK);

    }

    
}
