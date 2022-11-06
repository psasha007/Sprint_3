import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static constants.Consts.*;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;

public class CourierDeleteTest extends CourierDeleteApi{
    @Before
    public void setUp() {
        requestSpecification();
    }

    @Test
    @DisplayName("Проверка - успешный запрос возвращает ok: true")
    public void verifyDeleteMethodDelCourier() {
        CourierApi courierCreate = new CourierApi();

        courierCreate.courierCreateStatusCode(
                new CourierCreate(COURIER_LOGIN, COURIER_PASSWORD, COURIER_FIRST_NAME), SC_CREATED);

        Assert.assertTrue(
                deleteResponseJson(String.valueOf(
                        courierCreate.courierResponseJson(
                                new CourierLogin(COURIER_LOGIN, COURIER_PASSWORD)).getId()))
                        .isOk());
    }

    @Test
    @DisplayName("Проверка - если отправить запрос с несуществующим id, вернётся ошибка 404")
    public void verifyDeleteMethodDelCourierStatusCode404(){

        deleteVerifyStatusCode(String.valueOf(ID_COURIER_FALSE), SC_NOT_FOUND);
    }

    @Test
    @DisplayName("Проверка - неуспешный запрос возвращает соответствующую ошибку")
    public void verifyDeleteMethodDelCourierStatusMessage(){

        Assert.assertEquals("Курьера с таким id нет.",
                deleteResponseJson(String.valueOf(ID_COURIER_FALSE))
                        .getMessage());
    }

    @Test
    @DisplayName("Проверка - если отправить запрос без id, вернётся ошибка")
    public void verifyDeleteMethodDelCourierNotIdStatusMessage(){

        Assert.assertEquals("Not Found.", deleteResponseJson("").getMessage());
    }
}
