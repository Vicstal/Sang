package com.wg;

import javax.swing.*;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import java.awt.*;

import com.swtdesigner.SwingResourceManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.GregorianCalendar;

public class GUI2 extends JFrame implements ActionListener {
    // 主窗体初始界面
    private JTextField textField;
    private static Font font=new Font("宋体",Font.BOLD,18);
    private static JMenuItem 商品信息;

    GUI2(String temp) {

        JTextField 账号 = new JTextField();
        账号.setEditable(false);
        账号.setFont(font);
        GregorianCalendar ca = new GregorianCalendar();
		if(ca.get(GregorianCalendar.AM_PM)==0)
		{
			账号.setText("     上午好 ");
			账号.setFont(font);
			账号.setForeground(Color.black);
		}
		else 
		{
			账号.setText("     下午好 ");
			账号.setFont(font);
			账号.setForeground(Color.black);
		}
		账号.setBounds(320, 312, 81, 29);//账号.setBounds(229, 312, 81, 29);
        账号.setBorder(BorderFactory.createLineBorder(Color.red));
        getContentPane().add(账号);
        final JLabel label = new JLabel();
        label.setIcon(SwingResourceManager
                .getIcon(GUI.class, "/com/img/sy.jpg"));
        label.setText("New JLabel");
        label.setBounds(0, 0, 770, 450);
        getContentPane().add(label);

        textField = new JTextField();
        textField.setText(temp);
        // textField.setBounds(0, 0, 97, 22);
        getContentPane().add(textField);

        // 添加快捷健
        JMenu jmaccount = new JMenu("帐户(A)");
        jmaccount.setFont(font);
        jmaccount.setMnemonic('A');
        JMenuBar jmb = new JMenuBar();
        jmb.add(jmaccount);

        final JMenuItem menuItem_2 = new JMenuItem();
        menuItem_2.addActionListener(e -> {
            WritePwd pwd = new WritePwd(textField.getText());
            pwd.setVisible(true);
        });
        menuItem_2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                InputEvent.CTRL_MASK));
        menuItem_2.setText("修改密码(S)");
        menuItem_2.setFont(font);
        jmaccount.add(menuItem_2);

        jmaccount.addSeparator();

        final JMenuItem menuItem_1 = new JMenuItem();
        menuItem_1.addActionListener(e -> {
            Login l = new Login();
            l.setVisible(true);
            dispose();
        });
        menuItem_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
                InputEvent.CTRL_MASK));
        menuItem_1.setText("注 销(Z)");
        menuItem_1.setFont(font);
        jmaccount.add(menuItem_1);

        jmaccount.addSeparator();
        jmaccount.add(get商品信息());
        /**
         * 人事管理
         */
        final JMenu menrs = new JMenu();
        menrs.setText("人事管理(R)");
        menrs.setFont(font);
        menrs.setMnemonic('R');
        jmb.add(menrs);

        final JMenuItem menuItem_10 = new JMenuItem();
        menuItem_10.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U,
                InputEvent.CTRL_MASK));
        menuItem_10.addActionListener(e -> {
            AddUser ad = new AddUser();
            ad.setVisible(true);
        });
        menuItem_10.setText("添加员工(U)");
        menuItem_10.setFont(font);
        menrs.add(menuItem_10);

        menrs.addSeparator();

        final JMenuItem menuItem_11 = new JMenuItem();
        menuItem_11.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,
                InputEvent.CTRL_MASK));
        menuItem_11.addActionListener(e -> {
            RemveUser re = new RemveUser();
            re.setVisible(true);
        });
        menuItem_11.setText("开除员工(D)");
        menuItem_11.setFont(font);
        menrs.add(menuItem_11);
        /**
         * 用户
         */
        final JMenu menrc = new JMenu();
        menrc.setText("用户管理(Q)");
        menrc.setFont(font);
        menrc.setMnemonic('Q');
        jmb.add(menrc);

        final JMenuItem menuItem_13 = new JMenuItem();
        menuItem_13.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U,
                InputEvent.CTRL_MASK));
        menuItem_13.addActionListener(e -> {
            AddCst ad4 = new AddCst();
            ad4.setVisible(true);
        });
        menuItem_13.setText("添加用户(F)");
        menuItem_13.setFont(font);
        menrc.add(menuItem_13);

        menrc.addSeparator();

        final JMenuItem menuItem_14 = new JMenuItem();
        menuItem_14.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,
                InputEvent.CTRL_MASK));
        menuItem_14.addActionListener(e -> {
            RemveCst re4 = new RemveCst();
            re4.setVisible(true);
        });
        menuItem_14.setText("删除用户(Y)");
        menuItem_14.setFont(font);
        menrc.add(menuItem_14);
        /**
         * 商品管理
         */
        final JMenu mensp = new JMenu();
        mensp.setText("商品管理(S)");
        mensp.setFont(font);
        mensp.setMnemonic('S');
        jmb.add(mensp);

        final JMenuItem menuItem_7 = new JMenuItem();
        menuItem_7.addActionListener(e -> {
            Insert frame = new Insert();
            frame.setVisible(true);
        });
        menuItem_7.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,
                InputEvent.CTRL_MASK));
        menuItem_7.setText("添加商品(B)");
        menuItem_7.setFont(font);
        mensp.add(menuItem_7);
        mensp.addSeparator();

        final JMenuItem menuItem_9 = new JMenuItem();
        menuItem_9.addActionListener(e -> {
            Select s = new Select();
            s.setVisible(true);
        });
        menuItem_9.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
                InputEvent.CTRL_MASK));
        menuItem_9.setText("商品管理(E)");
        menuItem_9.setFont(font);
        mensp.add(menuItem_9);

        mensp.addSeparator();

        final JMenuItem menuItem_3 = new JMenuItem();
        menuItem_3.addActionListener(e -> {
            Accounts ac = new Accounts();
            ac.setVisible(true);
        });
        menuItem_3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
                InputEvent.CTRL_MASK));
        menuItem_3.setText("收银(O)");
        menuItem_3.setFont(font);
        mensp.add(menuItem_3);
        JMenu menhelp = new JMenu("帮助(H)");
        menhelp.setFont(font);
        menhelp.setMnemonic('H');
        jmb.add(menhelp);

        final JMenuItem menuItem_4 = new JMenuItem();
        menuItem_4.addActionListener(e -> JOptionPane.showMessageDialog(null, "请参阅帮助文档" + "\n" + "或与管理员联系"));
        menuItem_4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
        menuItem_4.setText("帮助内容");
        menuItem_4.setFont(font);
        menhelp.add(menuItem_4);
        menhelp.addSeparator();

        final JMenuItem menuItem_5 = new JMenuItem();
        menuItem_5.addActionListener(e -> JOptionPane.showMessageDialog(null, "Made by:" + "\n"
                + "1614010215" + "桑晓倩"));
        menuItem_5.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,
                InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
        menuItem_5.setText("关于我们(G)");
        menuItem_5.setFont(font);
        menhelp.add(menuItem_5);



        this.setJMenuBar(jmb);
        JDesktopPane jdp = new JDesktopPane();
        this.add(jdp);
        this.setSize(770, 450);//this.setSize(554, 399);
		this.setLocation(670, 400);//this.setLocation(180, 80);
        this.setFont(font);
        this.setVisible(true);
        setTitle("超市商品管理系统");
        Toolkit tool = this.getToolkit();// 窗口图标设置
        Image myimage = tool.getImage("img\\tp.jpg"); // 图片路径
        this.setIconImage(myimage); // 窗口图标设置
        this.setResizable(false);// 窗口锁死
        getContentPane().setLayout(null);

    }

    static JMenuItem get商品信息() {
        if (商品信息 == null) {
            商品信息 = new JMenuItem();
            商品信息.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
                    InputEvent.CTRL_MASK));
            商品信息.addActionListener(e -> System.exit(0));
            商品信息.setText("退出(X)");
            商品信息.setFont(font);
        }
        return 商品信息;
    }

    public void actionPerformed(ActionEvent e) {

    }

}
