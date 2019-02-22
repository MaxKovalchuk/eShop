package factoryDao;

import java.util.ResourceBundle;

import dao.UserDAO;

public abstract class DaoFactory {
	public abstract UserDAO getUserDao();
	
	public static UserDAO getUserDAO() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		ResourceBundle config = ResourceBundle.getBundle("config");
		return ((DaoFactory) Class.forName(config.getString("factoryClass")).newInstance()).getUserDao();
	}
}
