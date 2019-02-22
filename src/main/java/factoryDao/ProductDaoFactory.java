package factoryDao;

import java.util.ResourceBundle;

import dao.ProductDAO;
import dao.UserDAO;

public abstract class ProductDaoFactory {
	public abstract ProductDAO getProductDao();
	
	public static ProductDAO getProductDAO() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		ResourceBundle config = ResourceBundle.getBundle("config");
		return ((ProductDaoFactory) Class.forName(config.getString("productFactoryClass")).newInstance()).getProductDao();
	}
}
