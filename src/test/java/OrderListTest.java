import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static constants.Consts.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class OrderListTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    @DisplayName("Проверка, что при успешном response в теле ответа возвращается список заказов")
    @Description("Id Курьера : 119246")
    public void verifyGetMethodOrderListBody() {
        OrderListResponseJson orderListResponseJson =
        given()
                .header("Content-type", "application/json") // передача Content-type в заголовке для указания типа файла
                .when()
                .get(REQUEST_ORDER_LIST + ID_COURIER_TRUE) // отправка POST-запроса
                .body().as(OrderListResponseJson.class);

        MatcherAssert.assertThat(orderListResponseJson.getOrders().size(), notNullValue());
//        System.out.println("OrderId : " + orderListResponseJson.getOrders().get(0).getId());
//        System.out.println("CourierId : " + orderListResponseJson.getOrders().get(0).getCourierId());
//        System.out.println("FirstName : " + orderListResponseJson.getOrders().get(0).getFirstName());
//        System.out.println("LastName : " + orderListResponseJson.getOrders().get(0).getLastName());
//        System.out.println("Address : " + orderListResponseJson.getOrders().get(0).getAddress());
//        System.out.println("MetroStation : " + orderListResponseJson.getOrders().get(0).getMetroStation());
//        System.out.println("phone : " + orderListResponseJson.getOrders().get(0).getPhone());
//        System.out.println("rentTime : " + orderListResponseJson.getOrders().get(0).getRentTime());
//        System.out.println("deliveryDate : " + orderListResponseJson.getOrders().get(0).getDeliveryDate());
//        System.out.println("Track : " + orderListResponseJson.getOrders().get(0).getTrack());
//        System.out.println("color : " + orderListResponseJson.getOrders().get(0).getColor()[0]);
//        System.out.println("comment : " + orderListResponseJson.getOrders().get(0).getComment());
//        System.out.println("createdAt : " + orderListResponseJson.getOrders().get(0).getCreatedAt());
//        System.out.println("updatedAt : " + orderListResponseJson.getOrders().get(0).getUpdatedAt());
//        System.out.println("status : " + orderListResponseJson.getOrders().get(0).getStatus());
//        System.out.println(" **** ");
//        System.out.println("pageInfo page : " + orderListResponseJson.pageInfo.getPage());
//        System.out.println("pageInfo total : " + orderListResponseJson.pageInfo.getTotal());
//        System.out.println("pageInfo limit : " + orderListResponseJson.pageInfo.getLimit());
//        System.out.println(" **** ");
//        System.out.println("availableStations name : " + orderListResponseJson.availableStations.get(0).getName());
//        System.out.println("availableStations number : " + orderListResponseJson.availableStations.get(0).getNumber());
//        System.out.println("availableStations color : " + orderListResponseJson.availableStations.get(0).getColor());
//        System.out.println(" **** ");
//        System.out.println("Всего заказов : " + orderListResponseJson.getOrders().size());
    }

    @Test
    @DisplayName("Проверка статуса кода, когда успешное получение ответа списка заказов")
    @Description("Ожидаем статус кода  200")
    public void verifyGetMethodOrderListStatusCode200() {
        given()
                .header("Content-type", "application/json") // передача Content-type в заголовке для указания типа файла
                .when()
                .get(REQUEST_ORDER_LIST + ID_COURIER_TRUE) // отправка POST-запроса
                .then().assertThat().statusCode(STATUS_CODE_200);
    }

    @Test
    @DisplayName("Проверка, что курьер с идентификатором {courierId = 1} не найден")
    @Description("Id Курьера : 1")
    public void verifyGetMethodOrderListDontHaveCourierId() {
        OrderListResponseJson orderListResponseJson =
                given()
                        .header("Content-type", "application/json") // передача Content-type в заголовке для указания типа файла
                        .when()
                        .get(REQUEST_ORDER_LIST + ID_COURIER_FALSE) // отправка POST-запроса
                        .body().as(OrderListResponseJson.class);

//        System.out.println("Message : " + orderListResponseJson.getMessage());
        Assert.assertEquals("Курьер с идентификатором 1 не найден", orderListResponseJson.getMessage());
    }

    @Test
    @DisplayName("Проверка статуса кода, когда неуспешное получение ответа списка заказов")
    @Description("Ожидаем статус кода  404")
    public void verifyGetMethodOrderListStatusCode404() {
        given()
                .header("Content-type", "application/json") // передача Content-type в заголовке для указания типа файла
                .when()
                .get(REQUEST_ORDER_LIST + ID_COURIER_FALSE) // отправка POST-запроса
                .then().assertThat().statusCode(STATUS_CODE_404);
    }
}
