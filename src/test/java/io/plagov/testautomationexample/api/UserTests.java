package io.plagov.testautomationexample.api;

import io.plagov.testautomationexample.api.model.CreateOrUpdateUserRequest;
import io.plagov.testautomationexample.api.model.CreateUserResponse;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static io.plagov.testautomationexample.api.util.PropertiesUtil.getApiAccessToken;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.lessThan;

@Tag("api-tests")
class UserTests extends ApiBaseTest {

    private RequestSpecification requestSpec;
    private ResponseSpecification responseSpec;

    @BeforeEach
    void setUp() {
        requestSpec = new RequestSpecBuilder()
                .addHeader("Authorization", "Bearer %s".formatted(getApiAccessToken()))
                .setContentType(ContentType.JSON)
                .setBasePath("/v2/users")
                .log(LogDetail.HEADERS)
                .addFilter(new AllureRestAssured())
                .build();

        responseSpec = new ResponseSpecBuilder()
                .log(LogDetail.HEADERS)
                .expectResponseTime(lessThan(5_000L))
                .build();
    }

    @Test
    void shouldCreateNewUser() {
        var createUserPayload = getCreateUserPayload();
        var createUserResponse = createNewUser(createUserPayload);

        var getUserResponse = getUser(createUserResponse.id());

        assertThat(getUserResponse)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .describedAs("Get user response should be equal to create user response after user creation")
                .isEqualTo(createUserResponse);
    }

    @Test
    void shouldDeleteExistingUser() {
        var userId = Integer.MAX_VALUE;

        given()
                .spec(requestSpec)
                .pathParam("userId", userId)
                .when()
                .delete("{userId}")
                .then()
                .spec(responseSpec)
                .statusCode(204);

        given()
                .spec(requestSpec)
                .pathParam("userId", userId)
                .when()
                .get("{userId}")
                .then()
                .spec(responseSpec)
                .statusCode(404);
    }

    @Test
    void shouldUpdateUserEmail() {
        var createUserPayload = getCreateUserPayload();
        var createUserResponse = createNewUser(createUserPayload);

        var faker = new Faker();
        var updateUserPayload = new CreateOrUpdateUserRequest(
                null,
                null,
                "%s@%s".formatted(faker.job().field(), faker.domain().validDomain("flower")),
                null);

        given()
                .spec(requestSpec)
                .pathParam("userId", createUserResponse.id())
                .body(updateUserPayload)
                .when()
                .patch("{userId}")
                .then()
                .spec(responseSpec)
                .statusCode(200)
                .extract()
                .as(CreateUserResponse.class);

        var updatedUserResponse = getUser(createUserResponse.id());

        assertThat(updatedUserResponse).extracting("id", "name", "gender", "email", "status")
                .describedAs("User has not been updated correctly")
                .containsExactly(createUserResponse.id(),
                        createUserResponse.name(),
                        createUserResponse.gender(),
                        updateUserPayload.email(),
                        createUserResponse.status());
    }

    private CreateUserResponse createNewUser(CreateOrUpdateUserRequest createUserPayload) {
        return given()
                .spec(requestSpec)
                .when()
                .body(createUserPayload)
                .post()
                .then()
                .spec(responseSpec)
                .statusCode(201)
                .extract()
                .as(CreateUserResponse.class);
    }

    private CreateUserResponse getUser(int userId) {
        return given()
                .spec(requestSpec)
                .pathParam("userId", userId)
                .when()
                .get("{userId}")
                .then()
                .spec(responseSpec)
                .statusCode(200)
                .extract()
                .as(CreateUserResponse.class);
    }

    private CreateOrUpdateUserRequest getCreateUserPayload() {
        var faker = new Faker(new Locale("en"));
        var person = faker.name();

        return new CreateOrUpdateUserRequest(
                person.fullName(),
                faker.gender().binaryTypes().toLowerCase(),
                "%s.%s@%s".formatted(person.firstName(), person.lastName(), faker.domain().validDomain("acme")),
                faker.options().option("active", "inactive"));
    }
}
