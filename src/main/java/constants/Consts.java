package constants;

public class Consts {

    // Константа Url страницы про заказ самоката
    public static final String BASE_URL = "https://qa-scooter.praktikum-services.ru/";

    // ручка Orders - Создание заказа
    public static final String  REQUEST_CREATE_ORDERS = "/api/v1/orders";

    // ручка Courier - Логин курьера в системе
    public static final String  REQUEST_AUTORIZATION_COURIER = "/api/v1/courier/login";

    // ручка Orders - Получение списка заказов.
    public static final String  REQUEST_ORDER_LIST = "api/v1/orders?courierId=";

    // ручка Courier - Создание курьера
    public static final String  REQUEST_CREATE_NEW_COURIER = "/api/v1/courier";

    // ручка Courier - Удаление курьера
    public static final String  REQUEST_DELETE_COURIER = "/api/v1/courier/";
    
    // ручка Orders - Принять заказ
    public static final String  REQUEST_TO_ACCEPT_ORDERS = "/api/v1/orders/accept/";

    // ручка Orders - Получить заказ по его номеру
    public static final String  REQUEST_GET_ORDERS = "/api/v1/orders/track?t=";
    //
    public static final int  ID_ORDER_TRUE = 418743;
    public static final int  ID_ORDER_FALSE = 1;
    //
    public static final int  ID_COURIER_TRUE = 119246;
    public static final int  ID_COURIER_FALSE = 1;
    //
    public static final int ID_TRACK = 806087;
    public static final int STATUS_CODE_200 = 200;
    public static final int STATUS_CODE_201 = 201;
    public static final int STATUS_CODE_400 = 400;
    public static final int STATUS_CODE_404 = 404;
    public static final int STATUS_CODE_409 = 409;
}
