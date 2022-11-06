import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static constants.Consts.*;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.*;

@RunWith(value = Parameterized.class)
public class CourierLoginTest extends CourierLoginApi{

    private final String login;
    private final String password;
    private final int courierId;
    private final int statusCode;
    private final String displayName;

    public CourierLoginTest(String login, String password, int courierId, int statusCode, String displayName) {
        this.login = login;
        this.password = password;
        this.courierId = courierId;
        this.statusCode = statusCode;
        this.displayName = displayName;
    }

    @Before
    public void setUp() {
        requestSpecification();
    }

    @Parameterized.Parameters
    public static Object[] getSumData()
    {
        return new Object[][]
        {
            {"petya_ninja32", "12345", 119246, SC_OK, "Курьер может авторизоваться"},
            {"petya_ninja30", "12345", 119243, SC_OK, "Для авторизации нужно передать все обязательные поля"},
            {"petya_ninja333", "123456", 0, SC_NOT_FOUND, "Система вернёт ошибку, если неправильно указать логин или пароль"},
            {"petya_ninja32", "", 0, SC_BAD_REQUEST, "Если какого-то поля нет, запрос возвращает ошибку"},
            {"", "123456", 0, SC_BAD_REQUEST, "Если какого-то поля нет, запрос возвращает ошибку"},
            {"", "", 0, SC_BAD_REQUEST, "Если какого-то поля нет, запрос возвращает ошибку"},
            {"petya_ninja323", "123456", 0, SC_NOT_FOUND, "Если авторизоваться под несуществующим пользователем, запрос возвращает ошибку"},
            {"petya_ninja31", "12345", 119244, SC_OK, "Успешный запрос возвращает id"}
        };
    }

    @Test
//    @DisplayName(displayName)
//    @Description("Описание теста")
//    @TmsLink("TestCase-112") // ссылка на тест-кейс
//    @Issue("BUG-985") // ссылка на баг-репорт
    @DisplayName("Проверка авторизации курьера с разными значениями логина, пароля")
    public void verifyPostMethodAuthorizationcourierResponseJson() {

        Assert.assertEquals(courierId, loginResponseJson(new CourierLogin(login, password)).getId());
    }

    @Test
    // @DisplayName(displayName)
    // @Description("Описание теста")
    @DisplayName("Проверка статуса кода с разными значениями логина, пароля при авторизации курьера")
    public void verifyPostMethodAuthorizationCourierStatusCode() {

        loginStatusCode(new CourierLogin(login, password), statusCode);
    }
}
