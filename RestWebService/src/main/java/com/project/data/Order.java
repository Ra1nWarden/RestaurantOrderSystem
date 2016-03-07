package com.project.data;

public class Order {

	private int id;
	private int tableId;
	private String specialInstruction;
	private long timeCreated;

	public Order(int id, int tableId, String specialInstruction, long timeCreated) {
		this.id = id;
		this.tableId = tableId;
		this.specialInstruction = specialInstruction;
		this.timeCreated = timeCreated;
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

	public String getSpecialInstruction() {
		return specialInstruction;
	}

	public void setSpecialInstruction(String specialInstruction) {
		this.specialInstruction = specialInstruction;
	}

}
