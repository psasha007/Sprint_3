package constants;



public class Consts {

    // Константа Url страницы про заказ самоката
    public static final String BASE_URL = "https://qa-scooter.praktikum-services.ru/";

    // Константа ручка Orders - Создание заказа
    public static final String  REQUEST_CREATE_ORDERS = "/api/v1/orders";

    // Константа ручка Courier - Логин курьера в системе
    public static final String  REQUEST_AUTORIZATION_COURIER = "/api/v1/courier/login";

    // Константа ручка Orders - Получение списка заказов.
    public static final String  REQUEST_ORDER_LIST = "api/v1/orders?courierId=";

    // Константа ручка Courier - Создание курьера
    public static final String  REQUEST_CREATE_NEW_COURIER = "/api/v1/courier";

    // Константа ручка Courier - Удаление курьера
    public static final String  REQUEST_DELETE_COURIER = "/api/v1/courier/";
    
    // Константа ручка Orders - Принять заказ
    public static final String  REQUEST_TO_ACCEPT_ORDERS = "/api/v1/orders/accept/";

    // Константа ручка Orders - Получить заказ по его номеру
    public static final String  REQUEST_GET_ORDERS = "/api/v1/orders/track?t=";
    public static final int  ID_ORDER_TRUE = 418743;
    public static final int  ID_ORDER_FALSE = 1;
    public static final int  ID_COURIER_TRUE = 119246;
    public static final int  ID_COURIER_FALSE = 1;
    public static final int ID_TRACK = 806087;
    public static final String COURIER_LOGIN = "petya_ninja65";
    public static final String COURIER_PASSWORD = "12345";
    public static final String COURIER_FIRST_NAME = "firstName";
}
