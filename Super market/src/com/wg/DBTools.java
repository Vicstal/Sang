package com.wg;

import javax.swing.*;
import java.sql.*;

class DBTools {
	private Connection con;

	private Statement stmt;

	private ResultSet rs = null;

	DBTools() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver").newInstance();/*.newInstance()*/
		con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/wkdl?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false", "root", "root");
		stmt = (Statement)con.createStatement();
	}

	// 执行各种操作的方法,除了select语句
	int ExecuteSql(String sql) {
		try {
			stmt.execute(sql);
			return 1;
		} catch (Exception ex) {
			ex.printStackTrace();
			return -99;
		}
	}

	// 执行select的结果集
	ResultSet getSelectResult(String sql) {
		try {
			rs = stmt.executeQuery(sql);
			return rs;
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
			return null;
		}
	}

	// 查询结果集的数量
	int getResultSetCount(String tableName) {
		try {
			String str = "select * from " + tableName;
			rs = stmt.executeQuery(str);
			int num = 0;
			while (rs.next()) {
				num++;
			}
			return num;
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
			return -99;
		}
	}

	// 关闭数据库
	void Close() {
		try {
			stmt.close();
			con.close();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}

	// 预定义PrepareStatement
	boolean insertUserName(String name, String pwd) {
		String sql = "insert into userlogin (uName,uPassword) values(?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, pwd);
			ps.execute();
			return true;
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
			return false;
		}
	}
	// 插入新用户
		boolean insertCst(String phone, String name, String sex, String address) {
			String sql = "insert into customer (phone,name,sex,address) values(?,?,?,?)";
			try {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, phone);
				ps.setString(2, name);
				ps.setString(3, sex);
				ps.setString(4, address);
				ps.execute();
				return true;
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage());
				return false;
			}
		}

	// 添加新商品
	boolean insert(String bh, String name, String dj, String hc) {
		String sql = "insert into ware (id,wareName,price,entries,residual) values(?,?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, bh);
			ps.setString(2, name);
			ps.setString(3, dj);
			ps.setString(4, hc);
			ps.setString(5, "0");
			ps.execute();
			return true;
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
			return false;
		}
	}

	// 修改密码
	boolean updateUpwd(String name, String pwd) {

		String sql = "update userlogin set uPassword='" + pwd
				+ "'where uName='" + name + "'";
		try {
			stmt.executeQuery(sql);
			return true;
		} catch (Exception ex) {
			return true;
		}
	}

	// 进货
	boolean jh(String name, String id) {

		String sql = "update ware set entries=entries+"	+ Integer.valueOf(name.trim()) + "where id=" + id ;
		return sql(sql);
	}

	// 重置售出记录
	boolean sc(String id) {

		String sql = "update ware set residual=" + 0 + "where id="+ id ;
		return sql(sql);
	}

	private boolean sql(String sql) {
		try {
			stmt.execute(sql);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	// 改名称
	boolean gm(String name, String id) {

		String sql = "update ware set wareName='" + name.trim() + "'where id="+ id ;
		return sql(sql);
	}

	// 改单价
	boolean gj(String name, String id) {

		String sql = "update ware set price=" + Float.parseFloat(name.trim())
				+ "where id=" + id ;
		return sql(sql);
	}

	// Execute T-SQL,return resultset
	ResultSet select(String sql) {
		try {
			Statement cmd = con.createStatement();
			rs = cmd.executeQuery(sql);
			return rs;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}

}
