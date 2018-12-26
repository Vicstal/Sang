package com.wg;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;



import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * 用户端登录
 * @author Krus
 * @version win10
 */
public class Login3{
	private Color text6=new Color(237,237,237);
	private static final int DISPOSE_ON_CLOSE = 0;
	String name,password;
	boolean f=false;
	private Font font=new Font("宋体",Font.BOLD,18);
	private ImageIcon image=new ImageIcon("D:i/165.jpg");
	private Color color1=new Color(29,51,48);
	private JLabel imagelab=new JLabel(image);
	private JFrame frame=new JFrame("登录");
	private JButton submit=new JButton("确定");
	private JButton reset=new JButton("重置");
	private JLabel nameLab = new JLabel("账   户：");
	private JLabel passLab = new JLabel("密   码：");
	private JLabel infoLab = new JLabel("超市管理系统");
	 private JTextField nameText = new JTextField(15);
	 private JPasswordField passText = new JPasswordField(15);

	 private boolean choice=true;
	  public Login3(){
		submit.setFont(font);
		reset.setFont(font);

		nameLab.setFont(font);
		nameLab.setForeground(Color.white);
		passLab.setFont(font);
		passLab.setForeground(Color.white);
		infoLab.setFont(font);
		infoLab.setForeground(Color.white);

		frame.addWindowListener(new WindowAdapter(){
			public void windowClosed(WindowEvent e) {
			}
		});
		frame.setLayout(null); 
		nameLab.setBounds(45,115,100,50);   
		passLab.setBounds(45,140,100,50); 
		infoLab.setBounds(150,205,220,30); 
		nameText.setBounds(140,115,165,30);   
		passText.setBounds(140,145,165,30);  
		submit.setBounds(305,115,100,30); 
		reset.setBounds(305,145,100,30);   
		frame.add(nameLab);  
		frame.add(passLab);   
		frame.add(infoLab);  
		frame.add(nameText); 
		frame.add(passText);  
		frame.add(submit); 
		frame.add(reset);   
		frame.setSize(465,image.getIconHeight()+30); //���ô����С  
		imagelab.setBounds(0, 0, image.getIconWidth(),image.getIconHeight());
		frame.getLayeredPane().add(imagelab,new Integer(Integer.MIN_VALUE));
		frame.setFont(font);
	    frame.setResizable(false);
		frame.getContentPane().setBackground(Color.gray);
		Container cp=frame.getContentPane();  
		cp.setLayout(new BorderLayout());  
		((JPanel)cp).setOpaque(false);
		frame.setLocation(670,400); //���ô����ڵ��������ϵ�λ��   
		frame.setVisible(true); //��ʾ����  
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
		submit.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				name=nameText.getText().trim();
			    password=new String(passText.getPassword());
			    /*
			    DBTools db = null;
				try {
					db = new DBTools();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String sql = "userlogin where uName='"
						+ name + "' and uPassword='"
						+ password + "'";
				int x = db.getResultSetCount(sql);
			    */
			    
			    try {
					DBTools db = new DBTools();
					String sql = "userlogin where uName='"
							+ name + "' and uPassword='"
							+  password+ "'";
					int x = db.getResultSetCount(sql);

					if (x == 1) {
						JOptionPane.showMessageDialog(null, "欢迎登陆超市管理系统！");
						String str=nameText.getText();
						if (str.equals("sang")) 
						{
							frame.setVisible(false);
							new GUI2(nameLab.getText());
						} 
						else if(str.startsWith("b"))
						  {
							  frame.setVisible(false);
							  new GUIStore(nameLab.getText());
						  }
						  else 
						  {
							  frame.setVisible(false);
							  new GUIyg(nameLab.getText());
						  }
					}
					else 
					{
						JOptionPane.showMessageDialog(null, "用户名或密码错误!");
						nameText.setText("");
						passText.setText("");
						nameText.requestFocus();

					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			    
			    
			    /*
				  if(test()){
					 
					  if(test2())
					  {
						  String str=nameText.getText();
						  if(str.equals("sang"))
						  {
							  frame.setVisible(false);
							  new GUI2(nameLab.getText());
						  }
						  else if(str.startsWith("b"))
						  {
							  frame.setVisible(false);
							  new GUIStore(nameLab.getText());
						  }
						  else 
						  {
							  frame.setVisible(false);
							  new GUIyg(nameLab.getText());
						  }
					  }
						 
				  }else{
					  
				  }
				  */
			}	
        });
	}
/*
    public boolean test3()
    {
    	if(name.equals("sang")&&password.equals("123456"))
    	{
    		return true;
    	}
    	else if(name.equals("Krus")&&password.equals("123456"))
    	{
			return true;
		}
    	else if(name.equals("a01")&&password.equals("a01"))
    	{
    		return true;
    	}
    	else if(name.equals("a02")&&password.equals("a02"))
    	{
    		return true;
    	}
    	else if(name.equals("a03")&&password.equals("a03"))
    	{
    		return true;
    	}
    	else if(name.equals("a04")&&password.equals("a04"))
    	{
    		return true;
    	}
    	else if(name.equals("a05")&&password.equals("a05"))
    	{
    		return true;
    	}
    	else if(name.equals("a06")&&password.equals("a06"))
    	{
    		return true;
    	}
    	else if(name.equals("a07")&&password.equals("a07"))
    	{
    		return true;
    	}
    	else if(name.equals("a08")&&password.equals("a08"))
    	{
    		return true;
    	}
    	else if(name.equals("b01")&&password.equals("b01"))
    	{
    		return true;
    	}
    	else if(name.equals("3")&&password.equals("3"))
    	{
    		return true;
    	}
    	else {
			return false;
		}
    }
    
	public boolean test2(){
		 if(test3()){
			  return true;
		 }
		 else{
			 JOptionPane.showMessageDialog(null, "用户名密码错误", "Chat",JOptionPane.WARNING_MESSAGE);  
			 return false;
		 }
	}
*/
	public boolean test(){
		if("".equals(nameText.getText())||nameText.getText().length()==0||"".equals(passText.toString().trim())||passText.toString().length()==0||nameText.getText()==null||passText.toString()==null){
           JOptionPane.showMessageDialog(null, "账户不为空", "Chat",JOptionPane.WARNING_MESSAGE);  
       	   return false;
       }
		else{
			return true;
		}
	}
    public String getname(){
    	return name;
    }
    public String getpassword(){
    	return password;
    }
}