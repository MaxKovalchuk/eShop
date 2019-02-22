package factoryDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import dao.UserDAO;
import mySqlDao.MySqlUserDao;

public class MySqlDaoFactory extends DaoFactory{
	Connection conn;
	Statement st;
	
	public MySqlDaoFactory() {
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
	
	@Override
	public UserDAO getUserDao() {
		return new MySqlUserDao(conn);
	}

}
