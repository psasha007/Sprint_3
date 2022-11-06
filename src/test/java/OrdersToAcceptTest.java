import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static constants.Consts.*;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.*;

public class OrdersToAcceptTest extends OrdersToAcceptApi{

    String trackOrder;
    int orderId;

    @Before
    public void setUp() {
        requestSpecification();
        OrdersCreateApi ordersCreateApi = new OrdersCreateApi();
        trackOrder = ordersCreateApi.createOrdersResponseJson(new OrdersCreate("Naruto2", "Uchiha2",
                "Konoha2, 152 apt.2", 2, "+7 802 352 32 32", 5, "2022-10-30",
                "Saske2, come back to Konoha2", List.of("BLACK", "GREY"))).getTrack();
        OrdersGetApi ordersGetApi = new OrdersGetApi();
        orderId = ordersGetApi.ordersGetResponseJson(trackOrder).getOrder().getId();
    }

    @Test
    @DisplayName("Проверка - успешный запрос возвращает ok: true")
    @Description("Ожидаем статус кода  200")
    public void verifyPutMethodOrderToAcceptTrue() {

        orderToAcceptStatusCode(orderId, ID_COURIER_TRUE, SC_OK);
    }

    @Test
    @DisplayName("Проверка - если не передать id курьера, то запрос вернёт ошибку")
    @Description("Ожидаем статус кода 400")
    public void verifyPutMethodOrderToAcceptStatusCode400() {

        orderToAcceptStatusCode(orderId, "", SC_BAD_REQUEST);
    }

    @Test
    @DisplayName("Проверка - если передать неверный id курьера, то запрос вернёт ошибку")
    @Description("Ожидаем статус кода 400")
    public void verifyPutMethodOrderGetNotValidIdCourier() {

        orderToAcceptStatusCode(orderId, ID_COURIER_FALSE, SC_NOT_FOUND);
    }

    @Test
    @DisplayName("Проверка - если не передать номер заказа, то запрос вернёт ошибку")
    @Description("Ожидаем статус кода 404")
    public void verifyPutMethodOrderGetEmptyIdOrder() {

        orderToAcceptStatusCode("", ID_COURIER_FALSE, SC_NOT_FOUND);
    }

    @Test
    @DisplayName("Проверка - если передать неверный номер заказа, то запрос вернёт ошибку")
    @Description("Ожидаем статус кода 404")
    public void verifyPutMethodOrderGetValidIdOrder() {

        orderToAcceptStatusCode(ID_TRACK, ID_COURIER_TRUE, SC_NOT_FOUND);
    }
}
