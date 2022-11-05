import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static constants.Consts.*;
import static io.restassured.RestAssured.given;

public class CourierDeleteTest {
    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    @DisplayName("Проверка - успешный запрос возвращает ok: true")
    public void verifyDeleteMethodDelCourier() {
        CourierCreate courierCreate = new CourierCreate("petya_ninja65", "12345", "firstName");
        given()
                .header("Content-type", "application/json") // передача Content-type в заголовке для указания типа файла
                .and()
                .body(courierCreate) // передача объекта с данными
                .when()
                .post(REQUEST_CREATE_NEW_COURIER) // отправка POST-запроса
                .then().assertThat().statusCode(201);

        CourierLogin courierLogin = new CourierLogin("petya_ninja65", "12345");
        CourierLoginResponseJson courierJson = given()
                .header("Content-type", "application/json") // передача Content-type в заголовке для указания типа файла
                .and()
                .body(courierLogin) // передача объекта с данными
                .when()
                .post(REQUEST_AUTORIZATION_COURIER) // отправка POST-запроса
                .body().as(CourierLoginResponseJson.class);

        CourierDelete courierDelete = new CourierDelete(String.valueOf(courierJson.getId()));
        CourierDeleteResponseJson courierDeleteResponseJson = given()
                .header("Content-type", "application/json") // передача Content-type в заголовке для указания типа файла
                .and()
                .body(courierDelete)
                .when()
                .delete(REQUEST_DELETE_COURIER + String.valueOf(courierJson.getId()))
                .body().as(CourierDeleteResponseJson.class);

        Assert.assertTrue(courierDeleteResponseJson.isOk());
    }

    @Test
    @DisplayName("Проверка - если отправить запрос с несуществующим id, вернётся ошибка 404")
    public void verifyDeleteMethodDelCourierStatusCode404(){
        CourierDelete courierDelete = new CourierDelete(String.valueOf(ID_COURIER_FALSE));
        given()
                .header("Content-type", "application/json") // передача Content-type в заголовке для указания типа файла
                .and()
                .body(courierDelete)
                .when()
                .delete(REQUEST_DELETE_COURIER + String.valueOf(ID_COURIER_FALSE))
                .then().statusCode(404);
    }

    @Test
    @DisplayName("Проверка - неуспешный запрос возвращает соответствующую ошибку")
    public void verifyDeleteMethodDelCourierStatusMessage(){
        CourierDelete courierDelete = new CourierDelete(String.valueOf(ID_COURIER_FALSE));
        CourierDeleteResponseJson courierDeleteResponseJson = given()
                .header("Content-type", "application/json") // передача Content-type в заголовке для указания типа файла
                .and()
                .body(courierDelete)
                .when()
                .delete(REQUEST_DELETE_COURIER + String.valueOf(ID_COURIER_FALSE))
                .body().as(CourierDeleteResponseJson.class);

        Assert.assertEquals("Курьера с таким id нет.", courierDeleteResponseJson.getMessage());
    }

    @Test
    @DisplayName("Проверка - если отправить запрос без id, вернётся ошибка")
    public void verifyDeleteMethodDelCourierNotIdStatusMessage(){
        CourierDelete courierDelete = new CourierDelete("");
        CourierDeleteResponseJson courierDeleteResponseJson = given()
                .header("Content-type", "application/json") // передача Content-type в заголовке для указания типа файла
                .and()
                .body(courierDelete)
                .when()
                .delete(REQUEST_DELETE_COURIER)
                .body().as(CourierDeleteResponseJson.class);

        Assert.assertEquals("Not Found.", courierDeleteResponseJson.getMessage());
    }
}
