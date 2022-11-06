import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static constants.Consts.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static org.apache.http.HttpStatus.*;

public class CourierCreateTest extends CourierApi {
    @Before
    public void setUp() {
        requestSpecification();
    }

    @After
    public void setDown(){
        CourierDeleteApi courierDelete = new CourierDeleteApi();
        courierDelete.delete(String.valueOf(courierResponseJson(new CourierLogin(COURIER_LOGIN, COURIER_PASSWORD)).getId()));
    }

    @Test
    @DisplayName("Проверка - можно ли создать Курьера с заданными параметрами логин, пароль, имя курьера?")
    public void verifyPostMethodCreateNewCourier() {

        Assert.assertNull(courierCreateResponseJson(new CourierCreate(COURIER_LOGIN, COURIER_PASSWORD, COURIER_FIRST_NAME)).getTrack());
    }

    @Test
    @DisplayName("Проверка - Успешный запрос возвращает ok: true")
    public void verifyPostMethodCreateNewCourierIsTrue() {

        Assert.assertTrue(courierCreateResponseJson(new CourierCreate(COURIER_LOGIN, COURIER_PASSWORD, COURIER_FIRST_NAME)).isOk());
    }

    @Test
    @DisplayName("Проверка - кода ответа 201, с заданными параметрами логин, пароль, имя курьера")
    public void verifyPostMethodCreateNewCourierStatusCode() {

        courierCreateStatusCode(new CourierCreate(COURIER_LOGIN, COURIER_PASSWORD, COURIER_FIRST_NAME), SC_CREATED);
    }

    @Test
    @DisplayName("Проверка - что нельзя создать двух одинаковых курьеров")
    public void verifyPostMethodCreateTwinsNewCourier() {

        courierCreateStatusCode(new CourierCreate(COURIER_LOGIN, COURIER_PASSWORD, COURIER_FIRST_NAME), SC_CREATED);
        Assert.assertEquals(
                "Этот логин уже используется. Попробуйте другой.",
                courierCreateResponseJson(new CourierCreate(COURIER_LOGIN, COURIER_PASSWORD, COURIER_FIRST_NAME)).getMessage());
    }

    @Test
    @DisplayName("Проверка - возвращается ли ошибка, если создать пользователя с логином, который уже есть")
    public void verifyPostMethodCreateTwinsNewCourierStatusCode409() {

        courierCreateStatusCode(new CourierCreate(COURIER_LOGIN, COURIER_PASSWORD, COURIER_FIRST_NAME), SC_CREATED);
        courierCreateStatusCode(new CourierCreate(COURIER_LOGIN, COURIER_PASSWORD, COURIER_FIRST_NAME), SC_CONFLICT);
    }

    @Test
    @DisplayName("Проверка - если нет значения в поле login, то запрос возвращает ошибку")
    public void verifyPostMethodCreateNewCourierThenEmptyLoginStatusCode400() {

        courierCreateStatusCode(new CourierCreate("", COURIER_PASSWORD, COURIER_FIRST_NAME), SC_BAD_REQUEST);
    }

    @Test
    @DisplayName("Проверка - если нет значения в поле password, то запрос возвращает ошибку")
    public void verifyPostMethodCreateNewCourierThenEmptyPasswordStatusCode400() {

        courierCreateStatusCode(new CourierCreate(COURIER_LOGIN, "", COURIER_FIRST_NAME), SC_BAD_REQUEST);
    }

    @Test
    @DisplayName("Проверка - если нет значения в полей login и password, то запрос возвращает ошибку")
    public void verifyPostMethodCreateNewCourierThenEmptyLoginPasswordStatusCode400() {

        courierCreateStatusCode(new CourierCreate("", "", COURIER_FIRST_NAME), SC_BAD_REQUEST);
    }
}
