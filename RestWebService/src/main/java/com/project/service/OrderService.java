package com.project.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.project.data.Order;
import com.project.data.OrderDAO;

@Path("orders")
public class OrderService {

	private OrderDAO dao;

	public OrderService() {
		try {
			dao = new OrderDAO();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Order> getOrders() {
		try {
			return dao.getAllOrders();
		} catch(Exception e) {
			return null;
		}
	}
	
	@GET
	@Path("table/{table_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Order> getOrdersForTable(@PathParam("table_id") String tableId) {
		try {
			return dao.getOrdersForTable(Integer.parseInt(tableId));
		} catch(Exception e) {
			return null;
		}
	}

}
