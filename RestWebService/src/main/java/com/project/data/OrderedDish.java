package com.project.data;

public class OrderedDish {

	private String name;
	private int id;
	private float price;
	private Status status;

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

	public void setStatus(Status status) {
		this.status = status;
	}

	public Status getStatus() {
		return status;
	}

	public OrderedDish(String name, int id, float price, Status status) {
		this.name = name;
		this.id = id;
		this.price = price;
		this.status = status;
	}

	public enum Status {
		NOT_STARTED, PREPARING, DONE, CANCELLED
	}

	public static String convertStatusToString(Status status) {
		switch (status) {
		case NOT_STARTED:
			return "尚未准备";
		case PREPARING:
			return "准备中";
		case DONE:
			return "已完成";
		case CANCELLED:
			return "已取消";
		}
		return null;
	}

	public static Status convertStringToStatus(String string) {
		if (string.equals("尚未准备"))
			return Status.NOT_STARTED;
		else if (string.equals("准备中"))
			return Status.PREPARING;
		else if (string.equals("已完成"))
			return Status.DONE;
		else if (string.equals("已取消"))
			return Status.CANCELLED;
		return null;
	}

}
