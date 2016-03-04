package com.project.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.project.data.Dish;
import com.project.data.DishDAO;

@Path("dish")
public class DishService {
	
	private DishDAO dao;
	
	public DishService() {
		try {
			dao = new DishDAO();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Dish> getDishes() {
		try {
			return dao.getAllDishes();
		} catch(Exception e) {
			return null;
		}
	}
	

}
