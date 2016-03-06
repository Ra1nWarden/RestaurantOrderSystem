package com.project.wifiordersystem.data;

import java.util.Date;

/**
 * A POJO for displaying at home activity.
 */
public final class Order {

    private int id;
    private int tableId;
//    private float totalPrice;
//    private Date time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

//    public float getTotalPrice() {
//        return totalPrice;
//    }
//
//    public void setTotalPrice(float totalPrice) {
//        this.totalPrice = totalPrice;
//    }
//
//    public Date getTime() {
//        return time;
//    }
//
//    public void setTime(Date time) {
//        this.time = time;
//    }
}
