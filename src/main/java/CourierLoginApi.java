import static constants.Consts.REQUEST_AUTORIZATION_COURIER;
import static io.restassured.RestAssured.given;

public class CourierLoginApi extends BaseApi{

    public CourierLoginResponseJson loginResponseJson(CourierLogin courierLogin){
        return given()
                .header("Content-type", "application/json") // передача Content-type в заголовке для указания типа файла
                .and()
                .body(courierLogin) // передача объекта с данными
                .when()
                .post(REQUEST_AUTORIZATION_COURIER) // отправка POST-запроса
                .body().as(CourierLoginResponseJson.class);
    }

    public void loginStatusCode(CourierLogin courierLogin, int statusCode){
        given()
                .header("Content-type", "application/json") // передача Content-type в заголовке для указания типа файла
                .and()
                .body(courierLogin) // передача объекта с данными
                .when()
                .post(REQUEST_AUTORIZATION_COURIER) // отправка POST-запроса
                .then().assertThat().statusCode(statusCode);
    }
}
