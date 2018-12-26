package com.wg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Login extends JFrame {

	private JPasswordField 密码;

	private JTextField 账号;

	Login() {
		super();
		setTitle("登陆界面");
		Toolkit tool = this.getToolkit();// 窗口图标设置
		Image myimage = tool.getImage("img\\tp.jpg"); // 图片路径
		this.setIconImage(myimage); // 窗口图标设置
		this.setResizable(false);// 窗口锁死
		getContentPane().setLayout(null);
		setBounds(400, 400, 500, 500); //setBounds(100, 100, 262, 221);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		账号 = new JTextField();
		账号.setBounds(125, 38, 97, 22);
		getContentPane().add(账号);
		账号.addActionListener(new ListenerTextFieldtow());

		密码 = new JPasswordField();
		密码.setBounds(125, 88, 97, 26);
		密码.setEchoChar('\u25CF');
		getContentPane().add(密码);
		密码.addActionListener(new ListenerTextField());

		final JLabel lab_1 = new JLabel();
		lab_1.setBounds(35, 41, 60, 15);
		lab_1.setText("用户名：");
		getContentPane().add(lab_1);

		final JLabel lab_2 = new JLabel();
		lab_2.setBounds(35, 93, 60, 15);
		lab_2.setText("密  码：");
		getContentPane().add(lab_2);

		final JButton btn_1 = new JButton();
		btn_1.addActionListener(e -> {
			try {
				DBTools db = new DBTools();
				System.out.println("11111111");
				String sql = "userlogin where uName='"
						+ 账号.getText().trim() + "' and uPassword='"
						+ 密码.getText().trim() + "'";
				System.out.println(sql);
				int x = db.getResultSetCount(sql);

				if (x == 1) {
					JOptionPane.showMessageDialog(null, "欢迎登陆超市管理系统！");
					if (!账号.getText().equals("1614010215")) {
						new GUIyg(账号.getText());
						dispose();
					} else {
						new GUI(账号.getText());
						dispose();
					}
				} else {
					JOptionPane.showMessageDialog(null, "用户名或密码错误!");
					账号.setText("");
					密码.setText("");
					账号.requestFocus();

				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
		});
		btn_1.setBounds(35, 144, 60, 26);
		btn_1.setText("登陆");
		getContentPane().add(btn_1);

		final JButton btn_2 = new JButton();
		btn_2.addActionListener(e -> {
			账号.setText("");
			密码.setText("");
		});
		btn_2.setBounds(155, 144, 67, 26);
		btn_2.setText("清除");
		getContentPane().add(btn_2);

	}
	
//	 监听文本事件
	private class ListenerTextField implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			try {
				DBTools db = new DBTools();
				String sql = "userlogin where uName='"
						+ 账号.getText().trim() + "' and uPassword='"
						+ 密码.getText().trim() + "'";
				int x = db.getResultSetCount(sql);

				if (x == 1) {
					JOptionPane.showMessageDialog(null, "欢迎登陆超市管理系统！");
					if (!账号.getText().equals("1614010215")) {
						new GUIyg(账号.getText());
						dispose();
					} else {
						new GUI(账号.getText());
						dispose();
					}
				} else {
					JOptionPane.showMessageDialog(null, "用户名或密码错误!");
					账号.setText("");
					密码.setText("");
					账号.requestFocus();

				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
		}

	}
	
//	 监听文本事件
	private class ListenerTextFieldtow implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			密码.requestFocus();
		}
	}
}
