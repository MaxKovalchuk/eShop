package ua.itea.controllers;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import ua.itea.models.Product;
import ua.itea.models.User;

public class UserController {
	private DBWorker worker;
	private String salt = "labunskiy";

	public UserController(DBWorker worker) {
		this.worker = worker;
	}

	public User getUser(int id) {
		User user = new User();
		PreparedStatement pr;
		try {
			pr = worker.getConn().prepareStatement("SELECT * FROM users WHERE id =" + id);
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

	public User getUser(String login) {
		User user = new User();
		PreparedStatement pr;
		try {
			pr = worker.getConn().prepareStatement("SELECT * FROM users WHERE login ='" + login + "'");
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

	public boolean validateLogin(String login) {
		PreparedStatement pr;
		try {
			pr = worker.getConn().prepareStatement("SELECT * FROM users WHERE login = '" + login + "'");
			ResultSet rs = pr.executeQuery();
			if (!rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void insert(String login, String password, String name, String age, String gender, String address,
			String comment) {
		try {
			Statement st = worker.getConn().createStatement();
			String query = "INSERT INTO users(login, password, name, age, gender, address, comment) VALUES('" + login
					+ "', '" + hashString(password + salt) + "', '" + name + "', '" + Integer.parseInt(age) + "', '"
					+ gender + "', '" + address + "', '" + comment + "');";
			try {
				st.execute(query);
			} catch (MySQLIntegrityConstraintViolationException ex) {
				System.out.println("such email already exist");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
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

	public boolean authorizate(String login, String password) {
		PreparedStatement pr;
		try {
			pr = worker.getConn().prepareStatement("SELECT * FROM users WHERE login = '" + login + "' AND password = '"
					+ hashString(password + salt) + "'");
			ResultSet rs = pr.executeQuery();
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void logout(HttpServletRequest request, HttpSession session) {
		System.out.println(request.getAttribute("logout"));
		if (request.getAttribute("logout") != null) {
			System.out.println("we get here");
			if (session != null) {
				session.invalidate();
			}
		}
	}
}
