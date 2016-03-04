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

	private Order convertToOrder(ResultSet result) throws Exception {
		return new Order(result.getInt("id"), result.getInt("table_id"), result.getString("special_instruction"));
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

}
