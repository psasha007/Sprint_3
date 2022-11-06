import io.restassured.RestAssured;

import static constants.Consts.BASE_URL;

public class BaseApi {
    public void requestSpecification(){
        RestAssured.baseURI = BASE_URL;
    }
}
