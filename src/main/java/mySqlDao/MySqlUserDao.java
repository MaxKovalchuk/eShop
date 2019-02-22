package mySqlDao;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import dao.UserDAO;
import ua.itea.models.User;

public class MySqlUserDao implements UserDAO{
	private Connection conn;
	private String salt = "labunskiy";
	public MySqlUserDao(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public boolean createUser(User user) {
		try {
			Statement st = conn.createStatement();
			String query = "INSERT INTO users(login, password, name, age, gender, address, comment) VALUES('" + user.getLogin()
					+ "', '" + hashString(user.getPassword() + salt) + "', '" + user.getName() + "', '" + user.getAge() + "', '"
					+ user.getGender() + "', '" + user.getAddress() + "', '" + user.getComment() + "');";
			try {
				st.execute(query);
				return true;
			} catch (MySQLIntegrityConstraintViolationException ex) {
				System.out.println("such email already exist");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public User getUser(int id) {
		User user = new User();
		PreparedStatement pr;
		try {
			pr = conn.prepareStatement("SELECT * FROM users WHERE id =" + id);
			ResultSet rs = pr.executeQuery();
			if (rs.next()) {
				user.setId(rs.getInt("id"));
				user.setLogin(rs.getString("login"));
				user.setName(rs.getString("name"));
				user.setAge(rs.getInt("age"));
				user.setGender(rs.getString("gender"));
				user.setAddress(rs.getString("address"));
				user.setComment(rs.getString("comment"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	private String hashString(String hash) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}
		md5.update(StandardCharsets.UTF_8.encode(hash));
		return String.format("%032x", new BigInteger(md5.digest()));
	}

}
