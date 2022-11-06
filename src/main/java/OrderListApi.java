import static constants.Consts.*;
import static io.restassured.RestAssured.given;

public class OrderListApi extends BaseApi{
    public OrderListResponseJson orderListResponseJson(int courierId){
        return given()
                        .header("Content-type", "application/json") // передача Content-type в заголовке для указания типа файла
                        .when()
                        .get(REQUEST_ORDER_LIST + courierId) // отправка POST-запроса
                        .body().as(OrderListResponseJson.class);
    }

    public void orderListStatusCode(int courierId, int statusCode){
        given()
                .header("Content-type", "application/json") // передача Content-type в заголовке для указания типа файла
                .when()
                .get(REQUEST_ORDER_LIST + courierId) // отправка POST-запроса
                .then().assertThat().statusCode(statusCode);
    }
}
