import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static constants.Consts.*;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;

public class OrdersGetTest extends OrdersGetApi{
    @Before
    public void setUp() {
        requestSpecification();
    }

    @Test
    @DisplayName("Проверка - успешный запрос возвращает объект с заказом")
    @Description("Ожидаем ordersGetResponseJson объект с заказом")
    public void verifyPutMethodOrderToAccept() {

        Assert.assertTrue( ordersGetResponseJson(ID_ORDER_TRUE).getOrder() != null );
    }

    @Test
    @DisplayName("Проверка - запроса без номера заказа возвращает ошибку")
    @Description("Ожидаем статус кода 400")
    public void verifyPutMethodOrderToAcceptStatusCode200() {

        ordersGetStatusCode("", SC_BAD_REQUEST);
    }

    @Test
    @DisplayName("Проверка - запроса с несуществующим заказом возвращает ошибку")
    @Description("Ожидаем статус кода 404")
    public void verifyPutMethodOrderToAccept0() {

        ordersGetStatusCode(ID_ORDER_FALSE, SC_NOT_FOUND);
    }
}
