package dao;

import ua.itea.models.Product;

public interface ProductDAO {
	boolean createProduct(Product product);
	
	Product getProduct(int id);
}
