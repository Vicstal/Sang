package com.wg;

import javax.swing.*;
import java.awt.*;

class WritePwd extends JFrame {
	private Color color1=new Color(86,163,108);//藏蓝
    private JTextField 账号;

    private JPasswordField 确认密码;

    private JPasswordField 密码;

    WritePwd(String temp) {
        super();
        setTitle("修改密码");
        Toolkit tool = this.getToolkit();// 窗口图标设置
        Image myimage = tool.getImage("img\\tp.jpg"); // 图片路径
        this.setIconImage(myimage); // 窗口图标设置
        this.setResizable(false);// 窗口锁死
        dispose();
        getContentPane().setLayout(null);
        setBounds(500, 500, 293, 252);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


		this.setBackground(Color.blue);//11111111111111
		this.getContentPane().setBackground(color1);
		//如果改为true那么就变成了红色。
		this.getContentPane().setVisible(true);
		
        final JButton button = new JButton();
        button.addActionListener(e -> {
            try {

                if (!账号.getText().equals("")
                        && 密码.getText().trim().equals(
                        确认密码.getText().trim())
                        && !密码.getText().trim().equals("")
                        && !确认密码.getText().trim().equals("")) {
                    DBTools db = new DBTools();
                    boolean b = db.updateUpwd(账号.getText().trim(),
                            密码.getText().trim());
                    if (b) {
                        JOptionPane.showMessageDialog(null, "密码修改成功！");
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "密码修改失败，可能是您填写两次密码不一致！");
                    }
                } else {
                    JOptionPane.showMessageDialog(null,
                            "密码修改失败，可能是您填写两次密码不一致，或者为空！");
                }

                密码.setText("");
                确认密码.setText("");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });
        button.setText("确    定");
        button.setBounds(35, 170, 80, 28);
        getContentPane().add(button);

        final JButton button_1 = new JButton();
        button_1.addActionListener(e -> {
            密码.setText("");
            确认密码.setText("");
        });
        button_1.setText("重    置");
        button_1.setBounds(165, 170, 80, 28);
        getContentPane().add(button_1);

        final JLabel label = new JLabel();
        label.setFont(new Font("", Font.PLAIN, 14));
        label.setText("您的帐号");
        label.setBounds(35, 35, 72, 22);
        getContentPane().add(label);

        final JLabel label_1 = new JLabel();
        label_1.setFont(new Font("", Font.PLAIN, 14));
        label_1.setText("新的密码");
        label_1.setBounds(35, 74, 72, 22);
        getContentPane().add(label_1);

        final JLabel label_2 = new JLabel();
        label_2.setFont(new Font("", Font.PLAIN, 14));
        label_2.setText("重复密码");
        label_2.setBounds(35, 118, 72, 22);
        getContentPane().add(label_2);

        密码 = new JPasswordField();
        密码.setEchoChar('\u25CF');
        密码.setBounds(132, 75, 114, 22);
        getContentPane().add(密码);

        确认密码 = new JPasswordField();
        确认密码.setEchoChar('\u25CF');
        确认密码.setBounds(132, 119, 114, 22);
        getContentPane().add(确认密码);

        账号 = new JTextField();
        账号.setEditable(false);
        账号.setText(temp);
        账号.setBounds(132, 36, 114, 22);
        getContentPane().add(账号);

    }

}
