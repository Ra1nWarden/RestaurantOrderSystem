package com.project.data;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DishDAO {

	private Connection connection;

	public DishDAO() throws Exception {
		Properties prop = new Properties();
		FileInputStream fileStream = new FileInputStream("database.properties");
		prop.load(fileStream);

		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		String dburl = prop.getProperty("dburl");

		connection = DriverManager.getConnection(dburl, username, password);
	}

	public List<Dish> getAllDishes() throws Exception {
		List<Dish> ret = new ArrayList<Dish>();
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			statement = connection.prepareStatement("select * from restaurant_order_system.dishes");
			result = statement.executeQuery();
			while (result.next()) {
				ret.add(convertToDish(result));
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

	public List<OrderedDish> getDishesForOrder(int orderId) throws Exception {
		List<OrderedDish> ret = new ArrayList<OrderedDish>();
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			statement = connection.prepareStatement(
					"select * from restaurant_order_system.dishes o join restaurant_order_system.order_dishes d on o.id = d.dish_id where order_id = ?");
			statement.setInt(1, orderId);
			result = statement.executeQuery();
			while (result.next()) {
				ret.add(convertToOrderedDish(result));
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

	private Dish convertToDish(ResultSet result) throws Exception {
		return new Dish(result.getString("name"), result.getInt("id"), result.getFloat("price"));
	}

	private OrderedDish convertToOrderedDish(ResultSet result) throws Exception {
		return new OrderedDish(result.getString("name"), result.getInt("id"), result.getFloat("price"),
				OrderedDish.convertStringToStatus(result.getString("status")));
	}

}
