package ua.itea.controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ua.itea.models.Product;

public class ProductController {
	private DBWorker worker;
	final static private String SELECT_ALL = "SELECT * FROM products";

	public ProductController(DBWorker worker) {
		this.worker = worker;
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

	public List<Product> getSelectedProducts(int... categoryID) {
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

	public Product getProduct(int id) {
		Product product = new Product();
		PreparedStatement pr;
		try {
			pr = worker.getConn().prepareStatement("SELECT * FROM products WHERE id =" + id);
			ResultSet rs = pr.executeQuery();
			if (rs.next()) {
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getInt("price"));
				product.setDesc(rs.getString("description"));
				product.setCategory(rs.getInt("category"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}
}