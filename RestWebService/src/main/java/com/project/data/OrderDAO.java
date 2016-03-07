package com.project.data;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class OrderDAO {

	private Connection connection;

	public OrderDAO() throws Exception {
		Properties prop = new Properties();
		FileInputStream fileStream = new FileInputStream("database.properties");
		prop.load(fileStream);

		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		String dburl = prop.getProperty("dburl");

		connection = DriverManager.getConnection(dburl, username, password);
	}

	public List<Order> getAllOrders() throws Exception {
		List<Order> ret = new ArrayList<Order>();
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			statement = connection.prepareStatement("select * from restaurant_order_system.orders");
			result = statement.executeQuery();
			while (result.next()) {
				ret.add(convertToOrder(result));
			}
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (result != null) {
				result.close();
			}
		}
		return ret;
	}

	public float getTotalPriceForOrderId(int id) throws Exception {
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = connection.prepareStatement(
					"select sum(price) from dishes join order_dishes on dishes.id = order_dishes.dish_id where order_id = ?");
			statement.setInt(1, id);
			result = statement.executeQuery();
			if (result.next()) {
				return result.getFloat("sum(price)");
			} else {
				return 0.0f;
			}
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (result != null) {
				result.close();
			}
		}
	}

	private Order convertToOrder(ResultSet result) throws Exception {
		return new Order(result.getInt("id"), result.getInt("table_id"), result.getString("special_instruction"),
				result.getLong("time_created"));
	}

	public List<Order> getOrdersForTable(int parseInt) throws Exception {
		List<Order> ret = new ArrayList<Order>();
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			statement = connection.prepareStatement("select * from restaurant_order_system.orders where table_id = ?");
			statement.setInt(1, parseInt);
			result = statement.executeQuery();
			while (result.next()) {
				ret.add(convertToOrder(result));
			}
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (result != null) {
				result.close();
			}
		}
		return ret;

	}

	public void postOrder(NewOrder newOrder) throws Exception {
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			if (newOrder.getSpecialInstruction() == null) {
				statement = connection
						.prepareStatement("insert into restaurant_order_system.orders (table_id) values (?)");
				statement.setInt(1, newOrder.getTableId());
			} else {
				statement = connection.prepareStatement(
						"insert into restaurant_order_system.orders (table_id, special_instruction) values (?, ?)");
				statement.setInt(1, newOrder.getTableId());
				statement.setString(2, newOrder.getSpecialInstruction());
			}
			statement.execute();
			statement.close();
			statement = connection.prepareStatement("select LAST_INSERT_ID()");
			result = statement.executeQuery();
			if (result.next()) {
				int id = result.getInt(1);
				for (int each : newOrder.getDishIds()) {
					statement = connection.prepareStatement(
							"insert into restaurant_order_system.order_dishes (order_id, dish_id) values (?, ?)");
					statement.setInt(1, id);
					statement.setInt(2, each);
					if (statement.executeUpdate() == 0) {
						throw new IllegalArgumentException();
					}
				}
			}

		} finally {
			if (statement != null) {
				statement.close();
			}
			if (result != null) {
				result.close();
			}
		}
	}

}
