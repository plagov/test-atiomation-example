package io.plagov.testautomationexample.api;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class RestAssuredBaseConfig {

    @BeforeAll
    static void baseSetUp() {
        RestAssured.baseURI = "https://gorest.co.in/public";
    }
}
