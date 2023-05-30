package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

public class StoresAssertionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIT(){
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);

    }
    //1. Verify the if the total is equal to 1561
    @Test
    public void verifyTotal(){
        response.body("total", equalTo(1562));
    }

    //2. Verify the if the stores of limit is equal to 10
    @Test
    public void verifyLimit(){
        response.body("limit", equalTo(10));
    }

    //3. Check the single ‘Name’ in the Array list (Inver Grove Heights)
    @Test
    public void verifySingleName()
    { response.body("data.name", hasItem("Roseville33685"));}

    //4. Check the multiple ‘Names’ in the ArrayList (Roseville, Burnsville, Maplewood)
    @Test
    public void verifyMultipleNames()
    {
        response.body("data.name", hasItems( "Roseville33685"));
    }
    //5. Verify the store id=7 inside store services of the third store of second services
    @Test
    public void verifyStoreId7()
    {
        response.body("data[2].services[1].storeservices.storeId", equalTo(7));
    }

    //6. Check hash map values ‘createdAt’ inside storeservices map where store name = Roseville
    @Test
    public void verifyCreatedAt(){
        String createAt = response.extract().path("data[2].services[0].storeservices.createdAt");
        System.out.println("Created At " + createAt);
    }

    //7. Verify the state = MN of fourth store
    @Test
    public void verifyState() {
        String state = response.extract().path("data[3].state");
        System.out.println("State name is " + state);
    }
    //8. Verify the store name = Rochester of 9th store
    @Test
    public void verifyStoreName(){
        String store = response.extract().path("data[8].name");
        System.out.println("Store name is " + store);
    }
    //9. Verify the storeId = 11 for the 6th store
    @Test
    public void verifyStoreId(){
        int id = response.extract().path("data[5].id");
        System.out.println("6th Store Id is " + id);
        //data[5].id
    }
    //10. Verify the serviceId = 4 for the 7th store of fourth service
    @Test
    public void verifyServiceId () {
        int serviceId = response.extract().path("data[6].services[3].id");
        System.out.println("6th Store Id is " + serviceId);
        //data[6].services[3].id

    }

}
