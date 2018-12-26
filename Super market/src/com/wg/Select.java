package com.wg;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.sql.ResultSet;

class Select extends JFrame {
	private Font font=new Font("宋体",Font.BOLD,16);
	//#01b7ff
	private Color Color1=new Color(86,163,108);//藏蓝
    private final JTextField txtbh = new JTextField(10);

    private final JTextField txtmc = new JTextField();

    private final JTextField txtdj = new JTextField();

    private final JTextField txtsy = new JTextField();

    private final JTextField txtname = new JTextField();

    private final JTextField txtgm = new JTextField();

    private final JTextField txtsysl = new JTextField(10);

    private final JTextField txtgj = new JTextField(10);

    private ResultSet rs = null;

    private String scr = "";

    private String sdj = "";

    private String ssy = "";

    private String smc = "";

    Select() {
        super();
        setTitle("商品管理");
        Toolkit tool = this.getToolkit();// 窗口图标设置
        Image myimage = tool.getImage("img\\tp.jpg"); // 图片路径
        this.setIconImage(myimage); // 窗口图标设置
        this.setResizable(false);// 窗口锁死
        getContentPane().setLayout(null);
        setBounds(500, 500, 600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.setBackground(Color.blue);//11111111111111
		this.getContentPane().setBackground(Color1);
		//如果改为true那么就变成了红色。
		this.getContentPane().setVisible(true);
		
        final JLabel label = new JLabel();
        label.setText("商品编号");
        label.setFont(font);
		label.setForeground(Color.white);//111111111111
        label.setBounds(21, 32, 70, 15);
        getContentPane().add(label);

        final JLabel label_1 = new JLabel();
        label_1.setText("商品名称");
        label_1.setFont(font);
		label_1.setForeground(Color.white);//111111111111
        label_1.setBounds(21, 84, 70, 15);
        getContentPane().add(label_1);

        final JLabel label_2 = new JLabel();
        label_2.setText("单 价");
        label_2.setFont(font);
		label_2.setForeground(Color.white);//111111111111
        label_2.setBounds(21, 131, 70, 15);
        getContentPane().add(label_2);

        final JLabel label_3 = new JLabel();
        label_3.setText("剩余数量");
        label_3.setFont(font);
		label_3.setForeground(Color.white);//111111111111
        label_3.setBounds(21, 175, 70, 15);
        getContentPane().add(label_3);

        final JLabel label_4 = new JLabel();
        label_4.setText("卖出数量");
        label_4.setFont(font);
		label_4.setForeground(Color.white);//111111111111
        label_4.setBounds(21, 226, 70, 15);
        getContentPane().add(label_4);

        txtbh.setBounds(120, 28, 120, 22);
        txtbh.setDocument(new NumOnlyDocument());
        getContentPane().add(txtbh);

        txtname.setBounds(120, 80, 120, 22);
        txtname.setEditable(false);
        getContentPane().add(txtname);

        txtdj.setBounds(120, 127, 120, 22);
        txtdj.setEditable(false);
        getContentPane().add(txtdj);

        txtsy.setBounds(120, 171, 120, 22);
        txtsy.setEditable(false);
        getContentPane().add(txtsy);

        txtmc.setBounds(120, 222, 120, 22);
        txtmc.setEditable(false);
        getContentPane().add(txtmc);

        txtgm.setBounds(390, 79, 120, 25);
        getContentPane().add(txtgm);

        txtgj.setBounds(390, 126, 120, 25);
        txtgj.setDocument(new NumOnlyDocument());
        getContentPane().add(txtgj);

        txtsysl.setBounds(390, 170, 120, 25);
        txtsysl.setDocument(new NumOnlyDocument());
        getContentPane().add(txtsysl);

        final JButton button = new JButton();
        button.addActionListener(e -> {
            String temp = txtbh.getText();
            try {
                DBTools dbs = new DBTools();
                String sql = "select * from ware where id=" + temp.trim();
                rs = dbs.select(sql);
                if (rs.next()) {
                    scr = rs.getString("wareName");
                    txtname.setText("" + scr);
                    sdj = rs.getString("price");
                    txtdj.setText(sdj);
                    ssy = rs.getString("entries");
                    txtsy.setText(ssy);
                    smc = rs.getString("residual");
                    txtmc.setText(smc);
                } else {
                    JOptionPane.showMessageDialog(null, "没有此商品，请重新查询！");

                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        button.setText("查 询");
       // button.setHorizontalAlignment(JButton.LEFT);
        button.setFont(font);
        button.setForeground(Color.white);//111111111111
        button.setBounds(250, 29, 110, 22);
        getContentPane().add(button);

        final JButton button_1 = new JButton();
        button_1.addActionListener(e -> {
            txtbh.setText("");
            txtname.setText("");
            txtdj.setText("");
            txtsy.setText("");
            txtmc.setText("");
        });
        button_1.setText("重新查询");
        button_1.setFont(font);
       // button_1.setHorizontalAlignment(JButton.LEFT);
        button_1.setForeground(Color.white);//111111111111
        button_1.setBounds(390, 223, 120, 22);
        getContentPane().add(button_1);

        final JButton xgdj = new JButton();
        xgdj.addActionListener(e -> {
            String temp = txtbh.getText();
            try {

                if (!txtgj.getText().equals("")
                        && !txtbh.getText().equals("")) {
                    DBTools db = new DBTools();
                    String sqql = "select * from ware where id="
                            + temp.trim();
                    ResultSet cs = db.select(sqql);
                    if (cs.next()) {
                        db.Close();
                        DBTools dbsd = new DBTools();
                        boolean gj = dbsd
                                .gj(txtgj.getText(), txtbh.getText());
                        if (gj) {
                            JOptionPane.showMessageDialog(null, "单价修改成功！");
                            db.Close();
                            try {
                                DBTools dbs = new DBTools();
                                String sql = "select * from ware where id="
                                        + temp.trim();
                                rs = dbs.select(sql);
                                while (rs.next()) {

                                    scr = rs.getString("wareName");
                                    txtname.setText("" + scr);
                                    sdj = rs.getString("price");
                                    txtdj.setText(sdj);
                                    ssy = rs.getString("entries");
                                    txtsy.setText(ssy);
                                    smc = rs.getString("residual");
                                    txtmc.setText(smc);
                                    txtgj.setText("");

                                }
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }

                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "单价修改失败，商品编号无效！");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "单价修改失败，商品编号无效！");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "单价修改失败，不能为空！");

                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
            //
        });
        xgdj.setText("修改单价→");
        xgdj.setFont(font);
        xgdj.setHorizontalAlignment(JButton.LEFT);
        xgdj.setForeground(Color.white);
        xgdj.setBackground(Color.gray);//111111111111
        xgdj.setBounds(250, 128, 120, 22);
        getContentPane().add(xgdj);

        final JButton button_3 = new JButton();
        button_3.addActionListener(e -> {
            String temp = txtbh.getText();
            try {
                if (!txtsysl.getText().equals("")
                        && !txtbh.getText().equals("")) {
                    DBTools db = new DBTools();
                    String sqql = "select * from ware where id=" + temp.trim();
                    ResultSet cs = db.select(sqql);
                    if (cs.next()) {
                        db.Close();
                        DBTools dbs = new DBTools();
                        boolean dd = dbs.jh(txtsysl.getText(), txtbh.getText());
                        if (dd) {
                            db.Close();
                            JOptionPane.showMessageDialog(null, "进货成功！");
                            try {
                                DBTools dbst = new DBTools();
                                String sql = "select * from ware where id=" + temp.trim();
                                rs = dbst.select(sql);
                                while (rs.next()) {

                                    scr = rs.getString("wareName");
                                    txtname.setText("" + scr);
                                    sdj = rs.getString("price");
                                    txtdj.setText(sdj);
                                    ssy = rs.getString("entries");
                                    txtsy.setText(ssy);
                                    smc = rs.getString("residual");
                                    txtmc.setText(smc);
                                    txtsysl.setText("");

                                }
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }

                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "进货失败，商品编号无效！");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "进货失败，商品编号无效！");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "进货失败，不能为空！");

                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });
        button_3.setText("进 货→");
        button_3.setFont(font);
        button_3.setForeground(Color.white);//111111111111
        button_3.setBounds(250, 172, 120, 22);
        getContentPane().add(button_3);

        final JButton button_4 = new JButton();
        button_4.addActionListener(e -> {
            String temp = txtbh.getText();
            try {

                if (!txtgm.getText().equals("")
                        && !txtbh.getText().equals("")) {
                    DBTools db = new DBTools();
                    String sqql = "select * from ware where id="
                            + temp.trim();
                    ResultSet cs = db.select(sqql);
                    if (cs.next()) {
                        db.Close();
                        DBTools dbuv = new DBTools();
                        boolean gj = dbuv
                                .gm(txtgm.getText(), txtbh.getText());
                        if (gj) {
                            JOptionPane
                                    .showMessageDialog(null, "商品名称修改成功！");
                            db.Close();
                            try {
                                DBTools dbs = new DBTools();
                                String sql = "select * from ware where id="
                                        + temp.trim();
                                rs = dbs.select(sql);
                                while (rs.next()) {

                                    scr = rs.getString("wareName");
                                    txtname.setText("" + scr);
                                    sdj = rs.getString("price");
                                    txtdj.setText(sdj);
                                    ssy = rs.getString("entries");
                                    txtsy.setText(ssy);
                                    smc = rs.getString("residual");
                                    txtmc.setText(smc);
                                    txtgm.setText("");

                                }
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }

                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "商品名称修改失败，商品编号无效");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "商品名称修改失败，商品编号无效");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "商品名称修改失败，不能为空！");

                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }

        });
        button_4.setText("修改名称→");
        button_4.setFont(font);
        button_4.setForeground(Color.white);//111111111111
        button_4.setBounds(250, 81, 120, 22);
        getContentPane().add(button_4);

        final JButton button_2 = new JButton();
        button_2.addActionListener(e -> {
            try {
                String temp = txtbh.getText();
                if (!txtbh.getText().equals("")) {
                    DBTools db = new DBTools();
                    boolean dd = db.sc(txtbh.getText());
                    String sqc = "select * from ware where id=" + temp.trim();
                    rs = db.select(sqc);
                    if (rs.next()) {

                        if (dd) {
                            JOptionPane.showMessageDialog(null, "重新记录成功！");
                            txtmc.setText("0");
                        } else {
                            JOptionPane.showMessageDialog(null, "重新记录失败！");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "重新记录失败！");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "重新记录失败，不能为空！");

                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });
        button_2.setText("←重新记录");
        button_2.setFont(font);
        button_2.setForeground(Color.white);//111111111111
        button_2.setBounds(250, 222, 120, 22);
        getContentPane().add(button_2);

        final JButton button_5 = new JButton();
        button_5.addActionListener(e -> {
            String temp = txtbh.getText();
            try {
                if (!txtbh.getText().equals("")) {

                    try {
                        DBTools dbs = new DBTools();
                        String sql = "select * from ware where id="
                                + temp.trim();
                        rs = dbs.select(sql);
                        if (rs.next()) {
                            DBTools db = new DBTools();
                            String sq = "delete from ware where id="
                                    + temp.trim();
                            db.ExecuteSql(sq);
                            JOptionPane.showMessageDialog(null, "删除商品成功!");
                            txtbh.setText("");
                            txtname.setText("");
                            txtdj.setText("");
                            txtsy.setText("");
                            txtmc.setText("");
                            txtgm.setText("");
                            txtgj.setText("");
                            txtsysl.setText("");

                        } else {
                            JOptionPane.showMessageDialog(null, "删除商品失败!");
                        }

                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "商品编号不能为空!");
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        button_5.setText("删除商品");
        button_5.setFont(font);
        button_5.setBackground(Color.gray);
        button_4.setBackground(Color.gray);
        button_3.setBackground(Color.gray);
        button_2.setBackground(Color.gray);
        button_1.setBackground(Color.gray);
        
        button_2.setHorizontalAlignment(JButton.LEFT);
        button_3.setHorizontalAlignment(JButton.LEFT);
        button_4.setHorizontalAlignment(JButton.LEFT);
        //button_5.setHorizontalAlignment(JButton.LEFT);
        button.setBackground(Color.gray);
        button_5.setForeground(Color.white);//111111111111
        button_5.setBounds(390, 29, 120, 22);
        getContentPane().add(button_5);

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
