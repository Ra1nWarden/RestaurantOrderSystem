package com.project.data;

public class OrderedDish {

	private int orderId;
	private int dishId;
	private int tableId;
	private String dishName;
	private String status;
	private String specialInstruction;

	public OrderedDish(int orderId, int dishId, int tableId, String dishName, String status, String specialInstruction) {
		this.orderId = orderId;
		this.dishId = dishId;
		this.tableId = tableId;
		this.dishName = dishName;
		this.status = status;
		this.specialInstruction = specialInstruction;
	}

	public String getSpecialInstruction() {
		return specialInstruction;
	}

	public void setSpecialInstruction(String specialInstruction) {
		this.specialInstruction = specialInstruction;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getTableId() {
		return tableId;
	}

	public void setTableId(int tableId) {
		this.tableId = tableId;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public int getDishId() {
		return dishId;
	}

	public void setDishId(int dishId) {
		this.dishId = dishId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
