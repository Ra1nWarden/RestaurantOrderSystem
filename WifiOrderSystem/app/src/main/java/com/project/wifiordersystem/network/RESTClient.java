package com.project.wifiordersystem.network;

/**
 * A client for
 */
public final class RESTClient {

    private static final String BASE_URL = "http://192.168.0.114:8080/restaurant-web-service/rest";
    private static final String ORDERS_URL = "/orders";
    private static final String ORDER_URL = "/order";
    private static final String TABLE_URL = "/table";
    private static final String DISHES_URL = "/dishes";
    private static final String PRICE_URL = "/price";
    private static RESTClient instance;

    private RESTClient() {
    }

    public static RESTClient getInstance() {
        if (instance == null) {
            instance = new RESTClient();
        }
        return instance;
    }

    public String getOrderUrlForTable(int table) {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL);
        sb.append(ORDERS_URL);
        sb.append(TABLE_URL);
        sb.append("/");
        sb.append(table);
        return sb.toString();
    }

    public String getAllDishes() {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL);
        sb.append(DISHES_URL);
        return sb.toString();
    }

    public String getDishesForOrderId(int id) {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL);
        sb.append(DISHES_URL);
        sb.append(ORDER_URL);
        sb.append("/");
        sb.append(id);
        return sb.toString();
    }

    public String getTotalPriceForId(int id) {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL);
        sb.append(ORDERS_URL);
        sb.append(PRICE_URL);
        sb.append("/");
        sb.append(id);
        return sb.toString();
    }

}
