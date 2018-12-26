package com.wg;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.util.Objects;

public class RemveCst extends JFrame {
	private Color color1=new Color(86,163,108);//藏蓝
	private JComboBox<String> comboBox;
	private Font font=new Font("宋体",Font.BOLD,18);
	RemveCst() {
		super();
		setTitle("删除用户");
		Toolkit tool = this.getToolkit();// 窗口图标设置
		Image myimage = tool.getImage("img\\tp.jpg"); // 图片路径
		this.setIconImage(myimage); // 窗口图标设置
		this.setResizable(false);// 窗口锁死
		getContentPane().setLayout(null);
		setBounds(500, 500, 350, 350);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		this.setBackground(Color.blue);//11111111111111
		this.getContentPane().setBackground(color1);
		//如果改为true那么就变成了红色。
		this.getContentPane().setVisible(true);

		comboBox = new JComboBox<>();
		comboBox.setBounds(100, 43, 143, 30);
		comboBox.setFont(font);
		
		getContentPane().add(comboBox);
		try {
			DBTools db = new DBTools();
			ResultSet rs = db.getSelectResult("select * from customer");
			while (rs.next()) {
				comboBox.addItem(rs.getString("phone"));
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		final JButton button = new JButton();
		button.addActionListener(e -> 
		{
			String sname = Objects.requireNonNull(comboBox.getSelectedItem()).toString();
			if (sname.equals("")) 
			{
				javax.swing.JOptionPane
						.showMessageDialog(null, "你没有选中任何用户");
			} 
			else 
			{
				if (!sname.equals("sang")) 
				{

					try {
						DBTools db = new DBTools();
						String sql = "delete from customer where phone='"
								+ sname + "'";
						int x = db.ExecuteSql(sql);
						if (x == 1) 
						{
							javax.swing.JOptionPane.showMessageDialog(null,
									"删除用户成功!");
							comboBox.removeAllItems();
							ResultSet rs = db
									.getSelectResult("select * from customer");
							while (rs.next()) 
							{
								comboBox.addItem(rs.getString("phone"));
							}
						} 
						else 
						{
							javax.swing.JOptionPane.showMessageDialog(null,
									"删除用户失败!");
						}
					} catch (Exception ex) {
						javax.swing.JOptionPane.showMessageDialog(null, ex
								.getMessage());
					}

				} 
				else 
				{
					javax.swing.JOptionPane.showMessageDialog(null,
							"开除员工失败,不能开除管理员!");
				}

			}

		});
		button.setText("删除");
		button.setFont(font);//1111111111111111111111
		button.setBackground(Color.gray);//11111111111111
		button.setForeground(Color.white);
		button.setBounds(100, 160, 118, 30);
		getContentPane().add(button);
		//
	}

}
