package com.project.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

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
	
	@GET
	@Path("price/{order_id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getOrderTotalPrice(@PathParam("order_id") String orderId) {
		try {
			return Response.status(Status.OK)
					.entity(dao.getTotalPriceForOrderId(Integer.parseInt(orderId)))
					.build();
		} catch(Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.build();
		}
	}

}
