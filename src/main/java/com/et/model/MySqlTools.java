package com.et.model;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class MySqlTools {
	static Properties p = new Properties();
	static {
		InputStream is = MySqlTools.class.getResourceAsStream("/jdbc_mysql.properties");
		try {
			p.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static Connection getOracleConnection() throws Exception{
		String user = p.getProperty("user");
		String password = p.getProperty("password");
		String url =p.getProperty("url");
		String DriverClass = p.getProperty("DriverClass");
		Class.forName(DriverClass);
		Connection con = DriverManager.getConnection(url, user, password);
		System.out.println(con);
		return con;
	}
	
	
	public static List<Map> query(String sql) throws Exception{
		List list  = new ArrayList();
		Connection con = getOracleConnection();
		PreparedStatement p = con.prepareStatement(sql);
		ResultSet rs  = p.executeQuery();
		ResultSetMetaData rmd = rs.getMetaData();
		int count = rmd.getColumnCount();
		while(rs.next()){
			Map  map = new HashMap();
			for (int i = 1; i <= count; i++) {
				String name = rmd.getColumnName(i);
				String value= rs.getString(i);
				map.put(name, value);
			}
			list.add(map);
		}
		return list;
	} 
	public static int execute(String sql) throws Exception{
		Connection conn  = getOracleConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		int count =ps.executeUpdate();
		return count;
	}
}
