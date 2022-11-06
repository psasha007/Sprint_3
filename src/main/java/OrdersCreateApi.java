import static constants.Consts.REQUEST_CREATE_ORDERS;
import static io.restassured.RestAssured.given;

public class OrdersCreateApi extends BaseApi{

    public OrdersCreateResponseJson createOrdersResponseJson(OrdersCreate ordersCreate){
        return
                given()
                        .header("Content-type", "application/json") // передача Content-type в заголовке для указания типа файла
                        .and()
                        .body(ordersCreate) // передача объекта с данными
                        .when()
                        .post(REQUEST_CREATE_ORDERS) // отправка POST-запроса
                        .body().as(OrdersCreateResponseJson.class);
    }

    public void createOrdersStatusCode(OrdersCreate ordersCreate, int statusCode){
        given()
                .header("Content-type", "application/json") // передача Content-type в заголовке для указания типа файла
                .and()
                .body(ordersCreate) // передача объекта с данными
                .when()
                .post(REQUEST_CREATE_ORDERS) // отправка POST-запроса
                .then().assertThat().statusCode(statusCode);
    }
}
