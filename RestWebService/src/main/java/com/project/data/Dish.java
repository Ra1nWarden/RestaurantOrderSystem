package com.project.data;

public class Dish {
	
	private String name;
	private int id;
	private float price;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Dish(String name, int id, float price) {
		this.name = name;
		this.id = id;
		this.price = price;
	}

}
