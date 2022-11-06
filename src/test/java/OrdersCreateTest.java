import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static constants.Consts.*;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.Matchers.in;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(value = Parameterized.class)
public class OrdersCreateTest extends OrdersCreateApi{
    private final String firstName;
    private final String lastName;
    private final String address;
    private final int metroStation;
    private final String phone;
    private final int rentTime;
    private final String deliveryDate;
    private final String comment;
    private final List<String> color;

    public OrdersCreateTest(String firstName, String lastName, String address, int metroStation,
                            String phone, int rentTime, String deliveryDate, String comment, List<String> color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
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
// можно указать один из цветов — BLACK или GREY;
                        {"Naruto1", "Uchiha1", "Konoha1, 141 apt.1", 1, "+7 801 351 31 31", 5, "2022-11-04",
                                "Saske1, come back to Konoha1", List.of("BLACK")},
// можно указать оба цвета;
                        {"Naruto2", "Uchiha2", "Konoha2, 152 apt.2", 2, "+7 802 352 32 32", 6, "2022-10-30",
                                "Saske2, come back to Konoha2", List.of("BLACK", "GREY")},
// можно совсем не указывать цвет;
                        {"Naruto3", "Uchiha3", "Konoha3, 163 apt.3", 3, "+7 803 353 33 33", 7, "2022-10-29",
                                "Saske3, come back to Konoha3", List.of("")},
// тело ответа содержит track.
                        {"Naruto4", "Uchiha4", "Konoha4, 174 apt.4", 4, "+7 804 354 34 34", 8, "2022-10-28",
                                "Saske4, come back to Konoha4", List.of("BLACK", "GREY")}
                };
    }

    @Test
    @DisplayName("Проверка значения статуса кода")
    @Description("Ожидаем статус кода 201")
    public void verifyPostMethodCreateOrdersStatusCode() {

        createOrdersStatusCode(new OrdersCreate(firstName, lastName, address, metroStation, phone, rentTime,
                deliveryDate, comment, color), SC_CREATED);
    }

    @Test
    @DisplayName("Проверка - создания заказа")
    @Description("Ожидаем что значение track больше 0")
    public void verifyPostMethodCreateOrdersTrack() {

        int condition = Integer.parseInt(createOrdersResponseJson(
                new OrdersCreate(firstName, lastName, address, metroStation, phone, rentTime,
                        deliveryDate, comment, color)).getTrack());

        Assert.assertTrue(condition > 0);
    }
}
