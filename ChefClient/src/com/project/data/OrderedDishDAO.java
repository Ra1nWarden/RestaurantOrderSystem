package com.project.data;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class OrderedDishDAO {

	private static final String ORDER_ID_COLUMN = "order_id";
	private static final String TABLE_ID_COLUMN = "table_id";
	private static final String DISH_ID_COLUMN = "dish_id";
	private static final String DISH_NAME_COLUMN = "name";
	private static final String STATUS_COLUMN = "status";
	private static final String SPECIAL_INSTRUCTION_COLUMN = "special_instruction";
	private static final String ADVANCE_DISH_QUERY_STRING = "update order_dishes set status=? where order_id=? and dish_id=?";
	private static final String SELECT_ALL_QUERY_STRING = "select dishes.name, order_dishes.dish_id, order_dishes.order_id, orders.table_id, orders.special_instruction, order_dishes.status from dishes join order_dishes join orders on dishes.id = order_dishes.dish_id and orders.id = order_dishes.order_id where status=?";
	private Connection connection;

	public OrderedDishDAO() throws Exception {
		Properties prop = new Properties();
		FileInputStream fileStream = new FileInputStream("database.properties");
		prop.load(fileStream);

		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		String dburl = prop.getProperty("dburl");

		connection = DriverManager.getConnection(dburl, username, password);
	}

	public List<OrderedDish> getDishesForStatus(String status) {
		List<OrderedDish> ret = new ArrayList<OrderedDish>();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = connection.prepareStatement(SELECT_ALL_QUERY_STRING);
			statement.setString(1, status);
			result = statement.executeQuery();
			while (result.next()) {
				OrderedDish dish = convertToOrderedDish(result);
				if (dish != null) {
					ret.add(dish);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ret;
	}

	public int advanceDish(OrderedDish dish) {
		PreparedStatement statement = null;
		if (dish.getStatus().equals("尚未准备")) {
			try {
				statement = connection.prepareStatement(ADVANCE_DISH_QUERY_STRING);
				statement.setString(1, "准备中");
				statement.setInt(2, dish.getOrderId());
				statement.setInt(3, dish.getDishId());
				return statement.executeUpdate();
			} catch (Exception e) {
				return 0;
			}
		} else if (dish.getStatus().equals("准备中")) {
			try {
				statement = connection.prepareStatement(ADVANCE_DISH_QUERY_STRING);
				statement.setString(1, "已完成");
				statement.setInt(2, dish.getOrderId());
				statement.setInt(3, dish.getDishId());
				return statement.executeUpdate();
			} catch (Exception e) {
				return 0;
			}
		} else {
			return 0;
		}
	}

	private OrderedDish convertToOrderedDish(ResultSet resultSet) {
		try {
			return new OrderedDish(resultSet.getInt(ORDER_ID_COLUMN), resultSet.getInt(DISH_ID_COLUMN),
					resultSet.getInt(TABLE_ID_COLUMN), resultSet.getString(DISH_NAME_COLUMN),
					resultSet.getString(STATUS_COLUMN), resultSet.getString(SPECIAL_INSTRUCTION_COLUMN));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
