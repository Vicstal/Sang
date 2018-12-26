package com.wg;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class AddCst extends JFrame {

	private JTextField 性别;
	private Font font=new Font("宋体",Font.BOLD,18);
	private JTextField 姓名;
	private Color color1=new Color(86,163,108);//藏蓝
	private JTextField 电话;
	
	private JTextField 地址;

	AddCst() {
		super();
		setTitle("添加用户");
		Toolkit tool = this.getToolkit();// 窗口图标设置
		Image myimage = tool.getImage("D:1.jpg"); // 图片路径
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
		label.setFont(new Font("", Font.PLAIN, 14));
		label.setFont(font);
		label.setForeground(Color.white);//111111111111
		label.setText("电话：");
		label.setBounds(36, 27, 120, 22);
		getContentPane().add(label);

		final JLabel label_1 = new JLabel();
		label_1.setFont(new Font("", Font.PLAIN, 14));
		label_1.setFont(font);
		label_1.setForeground(Color.white);//111111111111
		label_1.setText("姓名：");
		label_1.setBounds(36, 73, 120, 20);
		getContentPane().add(label_1);

		final JLabel label_2 = new JLabel();
		label_2.setFont(new Font("", Font.PLAIN, 14));
		label_2.setFont(font);
		label_2.setForeground(Color.white);//111111111111
		label_2.setText("性别：");
		label_2.setBounds(36, 120, 120, 22);
		getContentPane().add(label_2);
		
		final JLabel label_3 = new JLabel();
		label_3.setFont(new Font("", Font.PLAIN, 14));
		label_3.setText("地址：");
		label_3.setForeground(Color.white);//111111111111
		label_3.setFont(font);
		label_3.setBounds(36, 166, 120, 22);
		getContentPane().add(label_3);

		电话 = new JTextField();
		电话.setBounds(154, 31, 130, 22);
		getContentPane().add(电话);

		姓名 = new JTextField();
		//姓名.setEchoChar('\u25CF');
		姓名.setBounds(154, 71, 130, 26);
		getContentPane().add(姓名);

		性别 = new JTextField();
		//性别.setEchoChar('\u25CF');
		性别.setBounds(154, 120, 130, 26);
		getContentPane().add(性别);
		
		地址 = new JTextField();
		//性别.setEchoChar('\u25CF');
		地址.setBounds(154, 166, 130, 26);
		getContentPane().add(地址);

		final JButton btnqd = new JButton();
		btnqd.addActionListener(e -> {
			try {

				if (!电话.getText().equals("")&&!姓名.getText().equals("")&&!性别.getText().equals("")&&!地址.getText().equals("")) 
				{
					DBTools db = new DBTools();
					String sql = "select * from customer Where phone='"+ 电话.getText().trim() +"'";
					ResultSet rs = db.getSelectResult(sql);
					if (rs.next()) 
					{
							电话.setText("");
							姓名.setText("");
							性别.setText("");
                            地址.setText("");
                            JOptionPane.showMessageDialog(null,	"该员工存在，请重新填写");

					}else
					{
						boolean b = db.insertCst(电话.getText()
								.trim(), 姓名.getText(),性别.getText(),地址.getText());
						if (b) 
						{
							JOptionPane.showMessageDialog(null, "用户添加成功");
						} else 
						{
							JOptionPane.showMessageDialog(null, "用户添加不成功");
						}
					}
				} else 
				{
					JOptionPane.showMessageDialog(null, "信息填写不完整");
				}


			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
		});
		btnqd.setText("确 定");
		btnqd.setFont(font);//111111111111111
		btnqd.setBackground(Color.gray);//11111111111111111
		btnqd.setBounds(36, 239, 90, 26);
		getContentPane().add(btnqd);

		final JButton btnqx = new JButton();
		btnqx.setFont(font);//111111111111111
		btnqx.setBackground(Color.gray);//11111111111111111
		btnqx.addActionListener(e -> dispose());
		btnqx.setText("取 消");
		btnqx.setBounds(177, 239, 90, 26);
		getContentPane().add(btnqx);

	}

}
