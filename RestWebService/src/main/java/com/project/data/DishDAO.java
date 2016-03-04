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

	private Dish convertToDish(ResultSet result) throws Exception {
		return new Dish(result.getString("name"), result.getInt("id"), result.getFloat("price"));
	}

}
