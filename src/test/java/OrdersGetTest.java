import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static constants.Consts.*;
import static io.restassured.RestAssured.given;

public class OrdersGetTest {
    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    @DisplayName("Проверка - успешный запрос возвращает объект с заказом")
    @Description("Ожидаем ordersGetResponseJson объект с заказом")
    public void verifyPutMethodOrderToAccept() {

        OrdersGetResponseJson ordersGetResponseJson =
        given()
                .header("Content-type", "application/json") // передача Content-type в заголовке для указания типа файла
                .when()
                .get(REQUEST_GET_ORDERS + ID_ORDER_TRUE) // отправка Get-запроса
                .body().as(OrdersGetResponseJson.class);

//        System.out.println(ordersGetResponseJson.getOrder().getId());
        Assert.assertTrue(ordersGetResponseJson.getOrder() != null);
    }

    @Test
    @DisplayName("Проверка - запроса без номера заказа возвращает ошибку")
    @Description("Ожидаем статус кода 400")
    public void verifyPutMethodOrderToAcceptStatusCode200() {
        given()
                .header("Content-type", "application/json") // передача Content-type в заголовке для указания типа файла
                .when()
                .get(REQUEST_GET_ORDERS + "") // отправка Get-запроса
                .then().assertThat().statusCode(STATUS_CODE_400);
    }

    @Test
    @DisplayName("Проверка - запроса с несуществующим заказом возвращает ошибку")
    @Description("Ожидаем статус кода 404")
    public void verifyPutMethodOrderToAccept0() {
        given()
                .header("Content-type", "application/json") // передача Content-type в заголовке для указания типа файла
                .when()
                .get(REQUEST_GET_ORDERS + ID_ORDER_FALSE) // отправка Get-запроса
                .then().assertThat().statusCode(STATUS_CODE_404);
    }
}
