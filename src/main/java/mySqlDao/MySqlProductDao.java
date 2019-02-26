package mySqlDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ProductDAO;
import ua.itea.models.Product;

public class MySqlProductDao implements ProductDAO {
	private Connection conn;

	public MySqlProductDao(Connection conn) {
		this.conn = conn;
	}

	@Override
	public Product getProduct(int id) {
		Product product = new Product();
		PreparedStatement pr;
		try {
			pr = conn.prepareStatement("SELECT * FROM products WHERE id =" + id);
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

	@Override
	public boolean createProduct(Product product) {
		return false;
	}

	@Override
	public List<Product> getProduct(String search) {
		PreparedStatement pr;
		List<Product> products = new ArrayList<Product>();
		Product product;
		try {
			pr = conn.prepareStatement(
					"SELECT * FROM products WHERE name LIKE '%" + search + "%' OR description LIKE '%" + search + "%'");
			ResultSet rs = pr.executeQuery();
			while (rs.next()) {
				product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getInt("price"));
				product.setDesc(rs.getString("description"));
				product.setCategory(rs.getInt("category"));
				products.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}

}
