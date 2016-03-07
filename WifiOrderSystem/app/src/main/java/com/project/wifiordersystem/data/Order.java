package com.project.wifiordersystem.data;

import java.io.Serializable;

/**
 * A POJO for displaying at home activity.
 */
public final class Order implements Serializable {

    private int id;
    private int tableId;
    private long timeCreated;
    private String specialInstruction;

    public String getSpecialInstruction() {
        return specialInstruction;
    }

    public void setSpecialInstruction(String specialInstruction) {
        this.specialInstruction = specialInstruction;
    }

    public long getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(long timeCreated) {
        this.timeCreated = timeCreated;
    }

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

}
