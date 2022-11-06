import static constants.Consts.*;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;

public class CourierApi extends BaseApi {
    public void courierCreateStatusCode(CourierCreate courierCreate, int statusCode){
         given()
                .header("Content-type", "application/json") // передача Content-type в заголовке для указания типа файла
                .and()
                .body(courierCreate) // передача объекта с данными
                .when()
                .post(REQUEST_CREATE_NEW_COURIER) // отправка POST-запроса
                .then().assertThat().statusCode(statusCode);
    }
    public CourierCreateResponseJson courierCreateResponseJson(CourierCreate courierCreate){
        return given()
                .header("Content-type", "application/json") // передача Content-type в заголовке для указания типа файла
                .and()
                .body(courierCreate) // передача объекта с данными
                .when()
                .post(REQUEST_CREATE_NEW_COURIER) // отправка POST-запроса
                .body().as(CourierCreateResponseJson.class);
    }

    public CourierLoginResponseJson courierResponseJson(CourierLogin courierLogin){
        return  given()
                .header("Content-type", "application/json") // передача Content-type в заголовке для указания типа файла
                .and()
                .body(courierLogin) // передача объекта с данными
                .when()
                .post(REQUEST_AUTORIZATION_COURIER) // отправка POST-запроса
                .body().as(CourierLoginResponseJson.class);
    }


}
