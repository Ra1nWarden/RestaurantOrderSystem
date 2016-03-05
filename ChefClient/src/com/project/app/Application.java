package com.project.app;

import java.awt.EventQueue;

import com.project.ui.OrderedDishesTableFrame;

public class Application {
	
	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderedDishesTableFrame loginWindow = new OrderedDishesTableFrame();
					loginWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
