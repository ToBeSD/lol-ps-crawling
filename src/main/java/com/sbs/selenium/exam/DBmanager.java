package com.sbs.selenium.exam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DBmanager {
	public Connection getConnection() throws ClassNotFoundException {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@211.45.162.83:1521:xe";
		String dbId = "yg_ac";
		String dbPW = "yg_ac";
		
		Class.forName(driver);
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, dbId, dbPW);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}