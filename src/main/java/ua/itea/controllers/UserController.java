package ua.itea.controllers;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import dao.UserDAO;
import factoryDao.DaoFactory;
import ua.itea.models.User;

public class UserController {
	private DBWorker worker;
	private String salt = "labunskiy";

	public UserController() {
		this.worker = new DBWorker();
	}

	public User getUser(int id) {
		UserDAO ud = null;
		try {
			ud = DaoFactory.getUserDAO();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return ud.getUser(id);
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

	public boolean insert(String login, String password, String name, String age, String gender, String address,
			String comment) {
		User user = new User();
		user.setLogin(login);
		user.setPassword(password);
		user.setName(name);
		user.setAge(Integer.parseInt(age));
		user.setGender(gender);
		user.setAddress(address);
		user.setComment(comment);
		UserDAO ud = null;
		try {
			ud = DaoFactory.getUserDAO();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return ud.createUser(user);
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

	public static boolean logout(HttpSession session, String logout) {
		if (logout != null) {
			if (session != null) {
				session.invalidate();
			}
			return true;
		}
		return false;
	}

	public boolean changeName(int id, String newName) {
		try {
			Statement st = worker.getConn().createStatement();
			String sql = "UPDATE users SET name = '" + newName + "' WHERE id = '" + id + "'";
			st.execute(sql);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean changeAddress(int id, String newAddress) {
		try {
			Statement st = worker.getConn().createStatement();
			String sql = "UPDATE users SET address = '" + newAddress + "' WHERE id = '" + id + "'";
			st.execute(sql);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
