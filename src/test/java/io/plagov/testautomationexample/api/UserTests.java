package io.plagov.testautomationexample.api;

import io.plagov.testautomationexample.model.CreateUserRequest;
import io.plagov.testautomationexample.model.CreateUserResponse;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static io.plagov.testautomationexample.api.util.PropertiesUtil.getApiAccessToken;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.lessThan;

class UserTests extends RestAssuredBaseConfig {

    private RequestSpecification requestSpec;
    private ResponseSpecification responseSpec;

    @BeforeEach
    void setUp() {
        requestSpec = new RequestSpecBuilder()
                .addHeader("Authorization", "Bearer %s".formatted(getApiAccessToken()))
                .setContentType(ContentType.JSON)
                .setBasePath("/v2/users")
                .log(LogDetail.ALL)
                .build();

        responseSpec = new ResponseSpecBuilder()
                .log(LogDetail.HEADERS)
                .expectResponseTime(lessThan(5_000L))
                .build();
    }

    @Test
    void shouldCreateNewUser() {
        var createUserPayload = getCreateUserRequest();

        var createUserResponse = given()
                .spec(requestSpec)
                .when()
                .body(createUserPayload)
                .post()
                .then()
                .spec(responseSpec)
                .statusCode(201)
                .extract()
                .as(CreateUserResponse.class);

        var getUserResponse = given()
                .spec(requestSpec)
                .pathParam("userId", createUserResponse.id())
                .when()
                .get("{userId}")
                .then()
                .spec(responseSpec)
                .statusCode(200)
                .extract()
                .as(CreateUserResponse.class);

        assertThat(getUserResponse)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .describedAs("Get user response not equal to create user response after user creation")
                .isEqualTo(createUserResponse);
    }

    private CreateUserRequest getCreateUserRequest() {
        var faker = new Faker(new Locale("en"));
        var person = faker.name();

        return new CreateUserRequest(
                person.fullName(),
                faker.gender().binaryTypes().toLowerCase(),
                "%s.%s@%s".formatted(person.firstName(), person.lastName(), faker.domain().validDomain("acme")),
                faker.options().option("active", "inactive"));
    }
}
