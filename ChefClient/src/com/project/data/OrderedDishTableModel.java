package com.project.data;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class OrderedDishTableModel extends AbstractTableModel {
	
	private String[] columnNames = { "菜名", "订单号", "桌号", "备注"};
	
	private List<OrderedDish> dishes = new ArrayList<OrderedDish>();
	
	public OrderedDishTableModel(List<OrderedDish> dishes) {
		this.dishes = dishes;
	}

	@Override
	public int getRowCount() {
		return dishes.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex) {
		case 0:
			return dishes.get(rowIndex).getDishName();
		case 1:
			return dishes.get(rowIndex).getOrderId();
		case 2:
			return dishes.get(rowIndex).getTableId();
		case 3:
			return dishes.get(rowIndex).getSpecialInstruction();
		}
		return null;
	}
	
	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	public OrderedDish valueAt(int row) {
		return dishes.get(row);
	}

}
