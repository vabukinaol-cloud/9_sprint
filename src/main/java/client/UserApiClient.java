package client;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import pojo.UserCreate;

import static io.restassured.RestAssured.given;

public class UserApiClient {

    private static final String BASE_URL = "https://stellarburgers.education-services.ru";

    @Step("Создать пользователя через API")
    public static String createUser(UserCreate user) {
        return given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post("/api/auth/register")
                .then()
                .statusCode(200)
                .extract()
                .path("accessToken");
    }

    @Step("Удалить пользователя через API")
    public static void deleteUser(String accessToken) {
        if (accessToken == null) return;
        given()
                .baseUri(BASE_URL)
                .header("Authorization", accessToken)
                .when()
                .delete("/api/auth/user")
                .then()
                .statusCode(202);
    }
}
