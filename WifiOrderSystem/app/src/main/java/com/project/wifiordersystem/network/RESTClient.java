package com.project.wifiordersystem.network;

/**
 * A client for
 */
public class RESTClient {

    private static final String BASE_URL = "http://192.168.0.114:8080/restaurant-web-service/rest";
    private static final String ORDERS_URL = "/orders";
    private static final String TABLE_URL = "/table";
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

}
