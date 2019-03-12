package ua.itea.controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import dao.ProductDAO;
import factoryDao.ProductDaoFactory;
import ua.itea.models.Product;

public class ProductController {
	private DBWorker worker;
	final static private String SELECT_ALL = "SELECT * FROM products";

	public ProductController() {
		this.worker = new DBWorker();
	}

	public List<Product> getAllProducts() {
		List<Product> productList = new ArrayList<Product>();
		try {
			PreparedStatement pr = worker.getConn().prepareStatement(SELECT_ALL);
			ResultSet rs = pr.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getInt("price"));
				product.setDesc(rs.getString("description"));
				product.setCategory(rs.getInt("category"));
				productList.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productList;
	}

	public List<Product> getSelectedCategoryProducts(int... categoryID) {
		List<Product> productList = new ArrayList<Product>();
		try {
			StringBuilder query = new StringBuilder();
			query.append(SELECT_ALL + " WHERE category = '");
			for (int i = 0; i < categoryID.length; i++) {
				if (i != categoryID.length - 1) {
					query.append(categoryID[i] + "' OR category = '");
				} else {
					query.append(categoryID[i] + "'");
				}
			}
			PreparedStatement pr = worker.getConn().prepareStatement(query.toString());
			ResultSet rs = pr.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getInt("price"));
				product.setDesc(rs.getString("description"));
				product.setCategory(rs.getInt("category"));
				productList.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productList;
	}

	public List<Product> getSelectedPageProducts(String page) {
		int n = Integer.parseInt(page);
		int firstId = n + ((n - 1) * 10) + (n - 1);
		List<Product> productList = new ArrayList<Product>();
		try {
			String query = SELECT_ALL + " WHERE id BETWEEN '" + firstId + "' AND '" + (firstId + 11) + "'";
			PreparedStatement pr = worker.getConn().prepareStatement(query);
			ResultSet rs = pr.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getInt("price"));
				product.setDesc(rs.getString("description"));
				product.setCategory(rs.getInt("category"));
				productList.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productList;
	}

	public Product getProduct(int id) {
		ProductDAO pd = null;
		try {
			pd = ProductDaoFactory.getProductDAO();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return pd.getProduct(id);
	}

	public List<Product> search(String search) {
		ProductDAO pd = null;
		try {
			pd = ProductDaoFactory.getProductDAO();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return pd.getProduct(search);
	}
}
