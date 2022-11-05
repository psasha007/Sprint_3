import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static constants.Consts.*;
import static constants.Consts.STATUS_CODE_200;
import static io.restassured.RestAssured.given;

public class OrdersToAcceptTest {

    String trackOrder;
    int OrderId;

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }
    public void CreateOrdersTrack(){
        OrdersCreate ordersCreate = new OrdersCreate("Naruto2", "Uchiha2", "Konoha2, 152 apt.2",
                2, "+7 802 352 32 32", 5, "2022-10-30",
                "Saske2, come back to Konoha2", List.of("BLACK", "GREY"));
        OrdersCreateResponseJson ordersCreateResponseJson =
                given()
                        .header("Content-type", "application/json") // передача Content-type в заголовке для указания типа файла
                        .and()
                        .body(ordersCreate) // передача объекта с данными
                        .when()
                        .post(REQUEST_CREATE_ORDERS) // отправка POST-запроса
                        .body().as(OrdersCreateResponseJson.class);

        trackOrder = ordersCreateResponseJson.getTrack();
//        System.out.println(ordersCreateResponseJson.getTrack());

        OrdersGetResponseJson ordersGetResponseJson =
                given()
                        .header("Content-type", "application/json") // передача Content-type в заголовке для указания типа файла
                        .when()
                        .get(REQUEST_GET_ORDERS + trackOrder) // отправка Get-запроса
                        .body().as(OrdersGetResponseJson.class);
        OrderId = ordersGetResponseJson.getOrder().getId();
//        System.out.println(ordersGetResponseJson.getOrder().getId());
    }

    @Test
    @DisplayName("Проверка - успешный запрос возвращает ok: true")
    @Description("Ожидаем статус кода  200")
    public void verifyPutMethodOrderToAcceptTrue() {
        CreateOrdersTrack();
        given()
                .header("Content-type", "application/json") // передача Content-type в заголовке для указания типа файла
                .when()
                .put(REQUEST_TO_ACCEPT_ORDERS + String.valueOf(OrderId) + "?courierId="+ ID_COURIER_TRUE) // отправка PUT-запроса
                .then().assertThat().statusCode(STATUS_CODE_200);
    }

    @Test
    @DisplayName("Проверка - если не передать id курьера, то запрос вернёт ошибку")
    @Description("Ожидаем статус кода 400")
    public void verifyPutMethodOrderToAcceptStatusCode400() {
        CreateOrdersTrack();
        given()
                .header("Content-type", "application/json") // передача Content-type в заголовке для указания типа файла
                .when()
                .put(REQUEST_TO_ACCEPT_ORDERS + String.valueOf(OrderId) + "?courierId=") // отправка PUT-запроса
                .then().assertThat().statusCode(STATUS_CODE_400);
    }

    @Test
    @DisplayName("Проверка - если передать неверный id курьера, то запрос вернёт ошибку")
    @Description("Ожидаем статус кода 400")
    public void verifyPutMethodOrderGetNotValidIdCourier() {
        CreateOrdersTrack();
        given()
                .header("Content-type", "application/json") // передача Content-type в заголовке для указания типа файла
                .when()
                .put(REQUEST_TO_ACCEPT_ORDERS + String.valueOf(OrderId) + "?courierId=" + ID_COURIER_FALSE) // отправка PUT-запроса
                .then().assertThat().statusCode(STATUS_CODE_404);
    }

    @Test
    @DisplayName("Проверка - если не передать номер заказа, то запрос вернёт ошибку")
    @Description("Ожидаем статус кода 404")
    public void verifyPutMethodOrderGetEmptyIdOrder() {
        given()
                .header("Content-type", "application/json") // передача Content-type в заголовке для указания типа файла
                .when()
                .put(REQUEST_TO_ACCEPT_ORDERS + "" + "?courierId=" + ID_COURIER_TRUE) // отправка PUT-запроса
                .then().assertThat().statusCode(STATUS_CODE_404);
    }

    @Test
    @DisplayName("Проверка - если передать неверный номер заказа, то запрос вернёт ошибку")
    @Description("Ожидаем статус кода 404")
    public void verifyPutMethodOrderGetValidIdOrder() {
        given()
                .header("Content-type", "application/json") // передача Content-type в заголовке для указания типа файла
                .when()
                .put(REQUEST_TO_ACCEPT_ORDERS + ID_TRACK + "?courierId=" + ID_COURIER_TRUE) // отправка PUT-запроса
                .then().assertThat().statusCode(STATUS_CODE_404);
    }
}
