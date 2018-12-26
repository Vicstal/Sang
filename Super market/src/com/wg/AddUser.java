package com.wg;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

class AddUser extends JFrame {

	private JPasswordField 确认密码;
	private Color color1=new Color(86,163,108);//藏蓝
	private JPasswordField 密码;
	private Font font=new Font("宋体",Font.BOLD,18);
	private JTextField 账号;

	AddUser() {
		super();
		setTitle("添加员工");
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

		final JLabel label = new JLabel();
		label.setFont(font);
		label.setText("业务员帐号：");
		label.setForeground(Color.white);//111111111111
		label.setBounds(36, 27, 120, 22);
		getContentPane().add(label);

		final JLabel label_1 = new JLabel();
		label_1.setFont(font);
		label_1.setForeground(Color.white);//111111111111
		label_1.setText("业务员密码：");
		label_1.setBounds(36, 73, 120, 20);
		getContentPane().add(label_1);

		final JLabel label_2 = new JLabel();
		label_2.setFont(font);
		label_2.setForeground(Color.white);//111111111111
		label_2.setText("确认密码：");
		label_2.setBounds(36, 120, 120, 22);
		getContentPane().add(label_2);

		账号 = new JTextField();
		账号.setBounds(154, 31, 130, 22);
		getContentPane().add(账号);

		密码 = new JPasswordField();
		密码.setEchoChar('\u25CF');
		密码.setBounds(154, 71, 130, 26);
		getContentPane().add(密码);

		确认密码 = new JPasswordField();
		确认密码.setEchoChar('\u25CF');
		确认密码.setBounds(154, 119, 130, 26);
		getContentPane().add(确认密码);

		final JButton btnqd = new JButton();
		btnqd.addActionListener(e -> {
			try {

				if (!账号.getText().equals("")
						&& 密码.getText().trim().equals(
								确认密码.getText().trim())
						&& !密码.getText().trim().equals("")
						&& !确认密码.getText().trim().equals("")) {
					DBTools db = new DBTools();
					String sql = "select * from UserLogin Where uName='"+ 账号.getText().trim() +"'";
					ResultSet rs = db.getSelectResult(sql);
					if (rs.next()) {

							JOptionPane.showMessageDialog(null,	"该员工存在，请重新填写");
							账号.setText("");
							密码.setText("");
							确认密码.setText("");


					}else{
						boolean b = db.insertUserName(账号.getText()
								.trim(), 密码.getText().trim());
						if (b) {
							JOptionPane.showMessageDialog(null, "员工添加成功");
						} else {
							JOptionPane.showMessageDialog(null, "员工添加不成功");
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "信息填写不完整或二次密码不一致！");
				}


			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
		});
		btnqd.setText("确定");
		btnqd.setFont(font);//111111111111111
		btnqd.setBackground(Color.gray);//11111111111111111
		btnqd.setBounds(36, 185, 90, 26);
		getContentPane().add(btnqd);

		final JButton btnqx = new JButton();
		btnqx.setFont(font);//1111111111111111111111
		btnqx.setBackground(Color.gray);//11111111111111
		btnqx.addActionListener(e -> dispose());
		btnqx.setText("取消");
		btnqx.setBounds(185, 185, 90, 26);
		getContentPane().add(btnqx);

	}

}
