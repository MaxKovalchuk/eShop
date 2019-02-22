package dao;

import ua.itea.models.User;

public interface UserDAO {
	boolean createUser(User user);
	
	User getUser(int id);
}
