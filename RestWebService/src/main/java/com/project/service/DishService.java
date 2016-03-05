package com.project.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.project.data.Dish;
import com.project.data.DishDAO;
import com.project.data.OrderedDish;

@Path("dishes")
public class DishService {

	private DishDAO dao;

	public DishService() {
		try {
			dao = new DishDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Dish> getDishes() {
		try {
			return dao.getAllDishes();
		} catch (Exception e) {
			return null;
		}
	}

	@GET
	@Path("order/{order_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<OrderedDish> getDishesForOrder(@PathParam("order_id") String orderId) {
		try {
			return dao.getDishesForOrder(Integer.parseInt(orderId));
		} catch (Exception e) {
			return null;
		}
	}

}
