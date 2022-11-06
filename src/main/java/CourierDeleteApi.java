import static constants.Consts.REQUEST_DELETE_COURIER;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;

public class CourierDeleteApi  extends BaseApi{

    public void delete(String courierId){
        given()
                .header("Content-type", "application/json")
                .when()
                .delete(REQUEST_DELETE_COURIER + courierId);
    }

    public void deleteVerifyStatusCode(String courierId, int statusCode){
        given()
                .header("Content-type", "application/json")
                .when()
                .delete(REQUEST_DELETE_COURIER + courierId)
                .then().statusCode(statusCode);
    }

    public CourierDeleteResponseJson deleteResponseJson(String courierId){
        return given()
                .header("Content-type", "application/json") // передача Content-type в заголовке для указания типа файла
                .when()
                .delete(REQUEST_DELETE_COURIER + courierId)
                .body().as(CourierDeleteResponseJson.class);
    }
}
