package com.yss.common;

import java.sql.*;

import org.apache.log4j.Logger;


public class DBConnect {

	private static Connection con = null;
	
	private DBConnect(){
	}
	
	public static Connection getConnection(){
		if(con != null){
			return con;
		}
		//加载数据库驱动类
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//获取数据库连接
		try {
			//TODO；参数分别为数据库IP，用户名，密码
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "yss_ta", "yss_ta");
		} catch (SQLException e) {
			
			Logger.getLogger(DBConnect.class).error("连接数据库失败");
			e.printStackTrace();
			return null;
		}
		Logger.getLogger(DBConnect.class).info("连接数据库成功");
		return con;
	}
}
