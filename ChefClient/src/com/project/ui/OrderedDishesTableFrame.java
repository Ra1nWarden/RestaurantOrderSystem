package com.project.ui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.ListSelectionModel;

import com.project.data.OrderedDish;
import com.project.data.OrderedDishDAO;
import com.project.data.OrderedDishTableModel;

public class OrderedDishesTableFrame extends JFrame {

	private static final String items[] = { "尚未准备", "准备中", "已完成" };
	private static final String buttonText[] = { "开始准备", "完成菜品", "已完成" };
	private JTable orderedDishesTable;
	private OrderedDishDAO dao;
	private JComboBox<String> typeSelection;
	private JButton nextButton;
	private Map<String, String> mappedText;

	/**
	 * Create the frame.
	 */
	public OrderedDishesTableFrame() throws Exception {
		setTitle("厨师管理系统");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 565, 412);

		dao = new OrderedDishDAO();
		mappedText = new HashMap<String, String>();
		for (int i = 0; i < items.length; i++) {
			mappedText.put(items[i], buttonText[i]);
		}

		JScrollPane scrollPane = new JScrollPane();

		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout
				.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addContainerGap()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 541,
												Short.MAX_VALUE)
										.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 541,
												Short.MAX_VALUE))
								.addContainerGap()));
		groupLayout
				.setVerticalGroup(
						groupLayout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
								groupLayout.createSequentialGroup().addContainerGap(36, Short.MAX_VALUE)
										.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 306,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(panel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
										.addContainerGap()));

		nextButton = new JButton(buttonText[0]);
		nextButton.setEnabled(true);
		nextButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selection = orderedDishesTable.getSelectedRow();
				if (selection == -1) {
					JOptionPane.showMessageDialog(OrderedDishesTableFrame.this, "请选中要操作的菜品。", "非法操作",
							JOptionPane.ERROR_MESSAGE);
				} else {
					OrderedDish dish = ((OrderedDishTableModel) orderedDishesTable.getModel()).valueAt(selection);
					if(dao.advanceDish(dish) > 0) {
						JOptionPane.showMessageDialog(OrderedDishesTableFrame.this, "修改成功！");
						loadData((String) typeSelection.getSelectedItem());
					} else {
						JOptionPane.showMessageDialog(OrderedDishesTableFrame.this, "未知错误，请联系管理员。", "错误",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			
		});
		panel.add(nextButton);

		typeSelection = new JComboBox<String>();
		for (String eachType : items) {
			typeSelection.addItem(eachType);
		}
		typeSelection.setSelectedIndex(0);
		typeSelection.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				String selected = (String) e.getItem();
				OrderedDishesTableFrame.this.loadData(selected);
				if(selected.equals(items[2])) {
					nextButton.setVisible(false);
				} else {
					nextButton.setVisible(true);
					nextButton.setText(mappedText.get(selected));
				}
			}
			
		});
		
		
		panel.add(typeSelection);

		orderedDishesTable = new JTable(new OrderedDishTableModel(dao.getDishesForStatus(items[0])));
		orderedDishesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(orderedDishesTable);
		getContentPane().setLayout(groupLayout);
	}

	public void loadData(String dishType) {
		orderedDishesTable.setModel(new OrderedDishTableModel(dao.getDishesForStatus(dishType)));
	}
}
