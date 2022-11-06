import static constants.Consts.*;
import static io.restassured.RestAssured.given;

public class OrdersToAcceptApi extends BaseApi {
    public void orderToAcceptStatusCode(String orderId, String courierId, int statusCode){
        given()
                .header("Content-type", "application/json") // передача Content-type в заголовке для указания типа файла
                .when()
                .put(REQUEST_TO_ACCEPT_ORDERS + orderId + "?courierId="+ courierId) // отправка PUT-запроса
                .then().assertThat().statusCode(statusCode);
    }

    public void orderToAcceptStatusCode(String orderId, int courierId, int statusCode){
        given()
                .header("Content-type", "application/json") // передача Content-type в заголовке для указания типа файла
                .when()
                .put(REQUEST_TO_ACCEPT_ORDERS + orderId + "?courierId="+ courierId) // отправка PUT-запроса
                .then().assertThat().statusCode(statusCode);
    }

    public void orderToAcceptStatusCode(int orderId, int courierId, int statusCode){
        given()
                .header("Content-type", "application/json") // передача Content-type в заголовке для указания типа файла
                .when()
                .put(REQUEST_TO_ACCEPT_ORDERS + orderId + "?courierId="+ courierId) // отправка PUT-запроса
                .then().assertThat().statusCode(statusCode);
    }

    public void orderToAcceptStatusCode(int orderId, String courierId, int statusCode){
        given()
                .header("Content-type", "application/json") // передача Content-type в заголовке для указания типа файла
                .when()
                .put(REQUEST_TO_ACCEPT_ORDERS + orderId + "?courierId="+ courierId) // отправка PUT-запроса
                .then().assertThat().statusCode(statusCode);
    }

    public void orderToAcceptResponseJson(int orderId, int courierId, int statusCode){
        given()
                .header("Content-type", "application/json") // передача Content-type в заголовке для указания типа файла
                .when()
                .put(REQUEST_TO_ACCEPT_ORDERS + orderId + "?courierId="+ courierId) // отправка PUT-запроса
                .then().assertThat().statusCode(statusCode);
    }
}
