import static constants.Consts.*;
import static io.restassured.RestAssured.given;

public class OrdersGetApi extends BaseApi{

    public OrdersGetResponseJson ordersGetResponseJson(int orderId){
     return
            given()
                    .header("Content-type", "application/json") // передача Content-type в заголовке для указания типа файла
                    .when()
                    .get(REQUEST_GET_ORDERS + orderId) // отправка Get-запроса
                    .body().as(OrdersGetResponseJson.class);
    }

    public OrdersGetResponseJson ordersGetResponseJson(String orderId){
        return
                given()
                        .header("Content-type", "application/json") // передача Content-type в заголовке для указания типа файла
                        .when()
                        .get(REQUEST_GET_ORDERS + orderId) // отправка Get-запроса
                        .body().as(OrdersGetResponseJson.class);
    }

    public void ordersGetStatusCode(int orderId, int statusCode){
        given()
                .header("Content-type", "application/json") // передача Content-type в заголовке для указания типа файла
                .when()
                .get(REQUEST_GET_ORDERS + orderId) // отправка Get-запроса
                .then().assertThat().statusCode(statusCode);
    }

    public void ordersGetStatusCode(String orderId, int statusCode){
        given()
                .header("Content-type", "application/json") // передача Content-type в заголовке для указания типа файла
                .when()
                .get(REQUEST_GET_ORDERS + orderId) // отправка Get-запроса
                .then().assertThat().statusCode(statusCode);
    }
}
