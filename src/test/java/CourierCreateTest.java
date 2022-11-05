import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static constants.Consts.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(value = Parameterized.class)
public class CourierCreateTest {
    private final String login;
    private final String password;
    private final String firstName;
    private final int responseCode;
    private final boolean isOk;
    private final String track;

    public CourierCreateTest(String login, String password, String firstName, int responseCode, boolean isOk, String track) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.responseCode = responseCode;
        this.isOk = isOk;
        this.track = track;
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    //@After
    public void setDown(){
        CourierLogin courierLogin = new CourierLogin("petya_ninja65", "12345");
        CourierLoginResponseJson courierJson = given()
                .header("Content-type", "application/json") // передача Content-type в заголовке для указания типа файла
                .and()
                .body(courierLogin) // передача объекта с данными
                .when()
                .post(REQUEST_AUTORIZATION_COURIER) // отправка POST-запроса
                .body().as(CourierLoginResponseJson.class);

        System.out.println(courierJson.getId());
        String tempID = String.valueOf(courierJson.getId());
        System.out.println(tempID);

        CourierDelete courierDelete = new CourierDelete(tempID);
        given()
                .header("Content-type", "application/json") // передача Content-type в заголовке для указания типа файла
                .and()
                .body(courierDelete)
                .when()
                .delete(REQUEST_DELETE_COURIER + tempID)
                .then().statusCode(STATUS_CODE_200);
    }

    @Parameterized.Parameters
    public static Object[] getSumData()
    {
        return new Object[][]
            {
                   {"petya_ninja65", "12345", "Petya", 201, true, null},
            };
    }

    @Test
    @DisplayName("Проверка - можно ли создать Курьера с заданными параметрами логин, пароль, имя курьера?")
    public void verifyPostMethodCreateNewCourier() {
        CourierCreate courierCreate = new CourierCreate(login, password, firstName);
        CourierCreateResponseJson courierCreateResponseJson =
        given()
                .header("Content-type", "application/json") // передача Content-type в заголовке для указания типа файла
                .and()
                .body(courierCreate) // передача объекта с данными
                .when()
                .post(REQUEST_CREATE_NEW_COURIER) // отправка POST-запроса
                .body().as(CourierCreateResponseJson.class);
        Assert.assertEquals(track, courierCreateResponseJson.getMessage());
        setDown();
    }

    @Test
    @DisplayName("Проверка - Успешный запрос возвращает ok: true")
    public void verifyPostMethodCreateNewCourierIsTrue() {
        CourierCreate courierCreate = new CourierCreate(login, password, firstName);
        CourierCreateResponseJson courierCreateResponseJson =
                given()
                        .header("Content-type", "application/json") // передача Content-type в заголовке для указания типа файла
                        .and()
                        .body(courierCreate) // передача объекта с данными
                        .when()
                        .post(REQUEST_CREATE_NEW_COURIER) // отправка POST-запроса
                        .body().as(CourierCreateResponseJson.class);
        Assert.assertEquals(isOk, courierCreateResponseJson.isOk());
        setDown();
    }

    @Test
    @DisplayName("Проверка - кода ответа, с заданными параметрами логин, пароль, имя курьера")
    public void verifyPostMethodCreateNewCourierStatusCode() {
        CourierCreate courierCreate = new CourierCreate(login, password, firstName);
        given()
                .header("Content-type", "application/json") // передача Content-type в заголовке для указания типа файла
                .and()
                .body(courierCreate) // передача объекта с данными
                .when()
                .post(REQUEST_CREATE_NEW_COURIER) // отправка POST-запроса
                .then().assertThat().statusCode(responseCode);
        setDown();
    }

    @Test
    @DisplayName("Проверка - что нельзя создать двух одинаковых курьеров")
    public void verifyPostMethodCreateTwinsNewCourier() {
        CourierCreate courierCreate = new CourierCreate(login, password, firstName);
        given()
                .header("Content-type", "application/json") // передача Content-type в заголовке для указания типа файла
                .and()
                .body(courierCreate) // передача объекта с данными
                .when()
                .post(REQUEST_CREATE_NEW_COURIER) // отправка POST-запроса
                .then().assertThat().statusCode(responseCode);

        CourierCreate courierCreate2 = new CourierCreate(login, password, firstName);
        CourierCreateResponseJson courierCreateResponseJson =
                given()
                        .header("Content-type", "application/json") // передача Content-type в заголовке для указания типа файла
                        .and()
                        .body(courierCreate2) // передача объекта с данными
                        .when()
                        .post(REQUEST_CREATE_NEW_COURIER) // отправка POST-запроса
                        .body().as(CourierCreateResponseJson.class);

        Assert.assertEquals("Этот логин уже используется. Попробуйте другой.", courierCreateResponseJson.getMessage());
        setDown();
    }

    @Test
    @DisplayName("Проверка - возвращается ли ошибка, если создать пользователя с логином, который уже есть")
    public void verifyPostMethodCreateTwinsNewCourierStatusCode() {
        CourierCreate courierCreate = new CourierCreate(login, password, firstName);
        given()
                .header("Content-type", "application/json") // передача Content-type в заголовке для указания типа файла
                .and()
                .body(courierCreate) // передача объекта с данными
                .when()
                .post(REQUEST_CREATE_NEW_COURIER) // отправка POST-запроса
                .then().assertThat().statusCode(responseCode);

        CourierCreate courierCreate2 = new CourierCreate(login, password, firstName);
        given()
                .header("Content-type", "application/json") // передача Content-type в заголовке для указания типа файла
                .and()
                .body(courierCreate2) // передача объекта с данными
                .when()
                .post(REQUEST_CREATE_NEW_COURIER) // отправка POST-запроса
                .then().assertThat().statusCode(STATUS_CODE_409);
        setDown();
    }

    @Test
    @DisplayName("Проверка - если нет значения в поле login, то запрос возвращает ошибку")
    public void verifyPostMethodCreateNewCourierThenEmptyLogin() {
        CourierCreate courierCreate = new CourierCreate("", password, firstName);
        given()
                .header("Content-type", "application/json") // передача Content-type в заголовке для указания типа файла
                .and()
                .body(courierCreate) // передача объекта с данными
                .when()
                .post(REQUEST_CREATE_NEW_COURIER) // отправка POST-запроса
                .then().assertThat().statusCode(STATUS_CODE_400);
    }

    @Test
    @DisplayName("Проверка - если нет значения в поле password, то запрос возвращает ошибку")
    public void verifyPostMethodCreateNewCourierThenEmptyPassword() {
        CourierCreate courierCreate = new CourierCreate(login, "", firstName);
        given()
                .header("Content-type", "application/json") // передача Content-type в заголовке для указания типа файла
                .and()
                .body(courierCreate) // передача объекта с данными
                .when()
                .post(REQUEST_CREATE_NEW_COURIER) // отправка POST-запроса
                .then().assertThat().statusCode(STATUS_CODE_400);
    }

    @Test
    @DisplayName("Проверка - если нет значения в полей login и password, то запрос возвращает ошибку")
    public void verifyPostMethodCreateNewCourierThenEmptyLoginPassword() {
        CourierCreate courierCreate = new CourierCreate("", "", firstName);
        given()
                .header("Content-type", "application/json") // передача Content-type в заголовке для указания типа файла
                .and()
                .body(courierCreate) // передача объекта с данными
                .when()
                .post(REQUEST_CREATE_NEW_COURIER) // отправка POST-запроса
                .then().assertThat().statusCode(STATUS_CODE_400);
    }
}
