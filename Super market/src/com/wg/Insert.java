package com.wg;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.sql.ResultSet;

class Insert extends JFrame {
	private Font font=new Font("宋体",Font.BOLD,18);
	private JTextField 库存;
	private Color color1=new Color(86,163,108);//藏蓝
	private JTextField 单价;

	private JTextField Name;

	private JTextField No;

	Insert() {
		super();
		setTitle("添加商品");
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
		label.setFont(new Font("", Font.PLAIN, 14));
		label.setFont(font);
		label.setForeground(Color.white);//111111111111
		label.setText("商品编号");
		label.setBounds(34, 36, 120, 15);
		getContentPane().add(label);

		final JLabel label_1 = new JLabel();
		label_1.setFont(new Font("", Font.PLAIN, 14));
		label_1.setFont(font);
		label_1.setForeground(Color.white);//111111111111
		label_1.setText("商品名称");
		label_1.setBounds(34, 80, 120, 15);
		getContentPane().add(label_1);

		final JLabel label_2 = new JLabel();
		label_2.setFont(new Font("", Font.PLAIN, 14));
		label_2.setFont(font);
		label_2.setForeground(Color.white);//111111111111
		label_2.setText("商品单价");
		label_2.setBounds(34, 126, 120, 15);
		getContentPane().add(label_2);

		final JLabel label_3 = new JLabel();
		label_3.setFont(new Font("", Font.PLAIN, 14));
		label_3.setFont(font);
		label_3.setForeground(Color.white);//111111111111
		label_3.setText("商品货存");
		label_3.setBounds(34, 170, 120, 15);
		getContentPane().add(label_3);

		No = new JTextField(10);
		No.setBounds(150, 33, 140, 22);//距离左边 距离上面 宽度 长度
		No.setDocument(new NumOnlyDocument());
		getContentPane().add(No);

		Name = new JTextField();
		Name.setBounds(150, 80, 140, 22);
		getContentPane().add(Name);

		单价 = new JTextField(10);
		单价.setBounds(150, 123, 140, 22);
		单价.setDocument(new NumOnlyDocument());
		getContentPane().add(单价);

		库存 = new JTextField();
		库存.setBounds(150, 167, 140, 22);
		库存.setDocument(new NumOnlyDocument());
		getContentPane().add(库存);

		final JButton button = new JButton();
		button.addActionListener(e -> {
			try {
				int res = 0;
				if (!No.getText().equals("")
						&& !Name.getText().equals("")
						&& !单价.getText().equals("")
						&& !库存.getText().equals("")) {
					DBTools db = new DBTools();
					String sql = "select * from ware";
					ResultSet rs = db.getSelectResult(sql);
					while (rs.next()) {
						if (rs.getString("id").equals(
								No.getText().trim())) {
							JOptionPane.showMessageDialog(null,
									"该商品已存在，请重新填写");
							No.setText("");
							Name.setText("");
							单价.setText("");
							库存.setText("");
							res = 1;
							break;
						}
					}
					if (res == 0) {
						boolean b = db.insert(No.getText().trim(), Name.getText().trim(), 单价.getText().trim(), 库存.getText().trim());
						if (b) {
							JOptionPane.showMessageDialog(null, "商品添加成功");
						} else {
							JOptionPane.showMessageDialog(null, "商品添加不成功");
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "信息填写不完整！");
				}

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}

		});
		button.setText("确认添加");
		button.setFont(font);//111111111111111
		button.setBackground(Color.gray);//11111111111111111
		button.setBounds(36, 250, 120, 26);
		getContentPane().add(button);

		final JButton button_1 = new JButton();
		button_1.addActionListener(e -> {
			No.setText("");
			Name.setText("");
			单价.setText("");
			库存.setText("");
		});
		button_1.setText("重新填写");
		button_1.setFont(font);//111111111111111
		button_1.setBackground(Color.gray);//11111111111111111
		button_1.setBounds(177, 250, 120, 26);
		getContentPane().add(button_1);

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
