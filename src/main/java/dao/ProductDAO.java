
package dao;

import java.util.List;

import ua.itea.models.Product;

public interface ProductDAO {
	boolean createProduct(Product product);
	
	Product getProduct(int id);
	
	List<Product> getProduct(String search);
}
