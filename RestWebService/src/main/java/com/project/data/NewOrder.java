package com.project.data;

import java.util.List;

public class NewOrder {
	
	private int tableId;
	private String specialInstruction;
	private List<Integer> dishIds;

	public int getTableId() {
		return tableId;
	}

	public void setTableId(int tableId) {
		this.tableId = tableId;
	}

	public List<Integer> getDishIds() {
		return dishIds;
	}

	public void setDishIds(List<Integer> dishIds) {
		this.dishIds = dishIds;
	}

	public String getSpecialInstruction() {
		return specialInstruction;
	}

	public void setSpecialInstruction(String specialInstruction) {
		this.specialInstruction = specialInstruction;
	}

}
