package com.wg;

import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import com.swtdesigner.SwingResourceManager;

class Accounts extends JFrame {

	private JTextArea TextArea;

	private final JTextField 编号 = new JTextField(10);
	private final JTextField 商品总价 = new JTextField();

	private final JTextField 数量 = new JTextField(10);

	private final JTextField 单价 = new JTextField();
	private final JTextField customer = new JTextField();
	private Color color1=new Color(86,163,108);//藏蓝
	
	private ResultSet 结果 = null;

	private String sdj = "";

	private String ssl = "";

	private String sname = "";

	private String str = "";

	private double 总价;

	private DBTools db;

	double d;
	Accounts() {
		super();
		setTitle("超市收银系统");// 标题名称
		Toolkit tool = this.getToolkit();// 窗口图标设置
		Image myimage = tool.getImage("img\\tp.jpg"); // 图片路径 img\\tp.jpg
		this.setIconImage(myimage); // 窗口图标设置
		this.setResizable(false);// 窗口锁死
		getContentPane().setLayout(null);
		
		this.setBackground(Color.blue);//11111111111111
		this.getContentPane().setBackground(color1);
		//如果改为true那么就变成了红色。
		this.getContentPane().setVisible(true);

		
		try {
			db = new DBTools();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		setBounds(500, 500, 441, 415);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		final JLabel label = new JLabel();
		label.setFont(new Font("", Font.BOLD, 20));
		label.setText("商品编号：");
		label.setBounds(10, 10, 108, 30);
		getContentPane().add(label);

		编号.setBounds(124, 17, 131, 22);
		getContentPane().add(编号);
		编号.setDocument(new NumOnlyDocument());
		编号.addActionListener(new ListenerTextField());
		

		商品总价.setBounds(124, 128, 131, 22);
		商品总价.setEditable(false);
		getContentPane().add(商品总价);

		单价.setEditable(false);
		单价.setBounds(124, 57, 131, 22);
		getContentPane().add(单价);

		数量.setBounds(124, 92, 131, 22);
		数量.setDocument(new NumOnlyDocument());
		getContentPane().add(数量);

		final JButton button = new JButton();
		button.addActionListener(e -> {
			JOptionPane.showMessageDialog(null,"  "+"名称"+"   "+"单价"+"   "+"数量"+"\n"+ "您应付款" + "          "+商品总价.getText()+"          "+"元");//+TextArea.getText()+"\n"
			商品总价.setText("");
			编号.setText("");
			数量.setText("");
			单价.setText("");
			TextArea.setText("");
			str="";
			总价 =0;
		});
		button.setText("结          账");
		button.setBounds(311, 207, 108, 36);
		getContentPane().add(button);

		final JButton button_1 = new JButton();
		button_1.addActionListener(e -> {
			编号.setText("");
			数量.setText("");
			单价.setText("");
//				TextArea.setText("");
		});
		button_1.setText("重置当前");
		button_1.setBounds(311, 265, 108, 36);
		getContentPane().add(button_1);

		final JButton button_2 = new JButton();
		button_2.addActionListener(e -> System.exit(0));
		button_2.setText("关 闭 系 统");
		button_2.setBounds(311, 319, 108, 36);
		getContentPane().add(button_2);

		final JLabel label_2 = new JLabel();
		label_2.setFont(new Font("", Font.BOLD, 20));
		label_2.setText("数       量：");
		label_2.setBounds(10, 85, 108, 30);
		getContentPane().add(label_2);

		final JLabel label_3 = new JLabel();
		label_3.setFont(new Font("", Font.BOLD, 20));
		label_3.setText("总       计：");
		label_3.setBounds(10, 121, 108, 30);
		getContentPane().add(label_3);

		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(67, 193, 224, 179);
		getContentPane().add(scrollPane);

		TextArea = new JTextArea();
		scrollPane.setViewportView(TextArea);
		TextArea.setBorder(BorderFactory.createLineBorder(Color.red));
		TextArea.setEditable(false);

		final JButton button_4 = new JButton();
		button_4.addActionListener(e -> {

			if (!编号.getText().equals("") && !单价.getText().equals("")
					&& !数量.getText().equals("")) {
				double price = Double.parseDouble(单价.getText());
				int number = Integer.parseInt(数量.getText().trim());
				String hao = 编号.getText();
				总价 = 总价 + price * number;
				商品总价.setText("" + 总价);
				try {

					db.ExecuteSql("update ware set entries=entries-"
							+ number + "where id=" + hao);
					// 返回结果集的方法
					int x = db.getResultSetCount("ware where id="
							+ hao.trim());
					if (x > 0) {
						db.ExecuteSql("update ware set residual=residual+"
								+ number + "where id=" + hao.trim());

						String temp = 编号.getText();

						DBTools dbs = new DBTools();
						String sql = "select * from ware where id="
								+ temp.trim();
						结果 = dbs.select(sql);
						if (结果.next()) {
							sname = 结果.getString("wareName");
							sdj = 结果.getString("price");
							ssl = 数量.getText().trim();
							str = str + "  "+ sname + "\t" + "  "+ sdj + "\t"+"     "+ ssl + "\n";
							TextArea.setText(str);
							//11111111
							商品总价.setText("");
							编号.setText("");
							数量.setText("");
							单价.setText("");
						} else {
							JOptionPane.showMessageDialog(null,
									"记录失败，请与管理员联系！");
						}

					} else {
						JOptionPane.showMessageDialog(null, "没有此类商品！");
					}

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null, "不能为空！");
			}
		});
		button_4.setText("记录信息");
		button_4.setBounds(311, 10, 108, 36);
		getContentPane().add(button_4);
		button.setBackground(Color.gray);
		button.setForeground(Color.white);
		button_1.setBackground(Color.gray);
		button_1.setForeground(Color.white);
		button_2.setBackground(Color.gray);
		button_2.setForeground(Color.white);
		button_4.setBackground(Color.gray);
		button_4.setForeground(Color.white);

		final JLabel label_1 = new JLabel();
		label_1.setFont(new Font("", Font.PLAIN, 14));
		label_1.setText("商品名称       商品单价       商品数量");
		label_1.setBounds(67, 157, 224, 30);
		getContentPane().add(label_1);

		final JLabel label_8 = new JLabel("<html>商<br><br>品<br><br>信<br><br>息</html> ");
		label_8.setFont(new Font("", Font.BOLD, 14));
		label_8.setBounds(22, 193, 23, 182);
		getContentPane().add(label_8);

		final JLabel label_4 = new JLabel();
		label_4.setFont(new Font("", Font.BOLD, 20));
		label_4.setText("单       价：");
		label_4.setBounds(9, 48, 109, 30);
		getContentPane().add(label_4);

		final JLabel label_5 = new JLabel();
		label_5.setIcon(SwingResourceManager.getIcon(Accounts.class,
				"/com/img/lg.gif"));
		label_5.setBounds(297, 57, 127, 128);
		getContentPane().add(label_5);

	}

	// 监听文本事件
	private class ListenerTextField implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String temp = 编号.getText();
			try {
				ResultSet set = db
						.getSelectResult("select price from ware where id='"
								+ temp.trim() + "'");
				if (set.next()) {
					d = set.getDouble("price");
					System.out.println("price="+d);
					Accounts.this.单价.setText("" + d);

				} else {
					Accounts.this.单价.setText("");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}
	
	class NumOnlyDocument extends PlainDocument {
		public void insertString(int offset, String s, AttributeSet attrSet)
				throws BadLocationException {
			try {
				Integer.parseInt(s);
			} catch (NumberFormatException ex) {
				return;
			}
			super.insertString(offset, s, attrSet);
		}
	}
	
}
