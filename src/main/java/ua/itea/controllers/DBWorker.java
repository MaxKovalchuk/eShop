package ua.itea.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class DBWorker {
	private final static String GET_USERS = "SELECT * FROM users";
	private final static String CHECK_USER = "SELECT name FROM users WHERE login=? AND password=?";
	Connection conn;
	Statement st;
	String salt = "labunskiy";

	public DBWorker() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception ex) {
		}
		System.out.print("Connection.....");
		try {
			ResourceBundle config = ResourceBundle.getBundle("config");
			conn = DriverManager.getConnection("jdbc:mysql://" + config.getString("host") + "/" + config.getString("db")
					+ "?" + "user=" + config.getString("user") + "&password=" + config.getString("psw"));
			System.out.println("obtained");
			st = conn.createStatement();
		} catch (SQLException ex) {
			System.out.println("failed...");
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public String getRows() {
		StringBuilder sb = new StringBuilder();
		try {
			ResultSet rs = st.executeQuery(GET_USERS);
			sb.append("<table border = '1'>");
			while (rs.next()) {
				sb.append("<tr><td>" + rs.getString(1) + "</td><td>" + rs.getString(2) + "</td><td>" + rs.getString(3)
						+ "</td><td>" + rs.getString(4) + "</td><td>" + rs.getString(5) + "</td><td>" + rs.getString(6)
						+ "</td><td>" + rs.getString(7) + "</td><td>" + rs.getString(8) + "</td></tr>");
			}
			sb.append("</table>");
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public void close() {
		try {
			if (st != null)
				st.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}