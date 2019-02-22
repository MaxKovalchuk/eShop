package factoryDao;

import com.thoughtworks.xstream.XStream;

import dao.UserDAO;
import xmlUserDao.XmlUserDao;

public class XmlDaoFactory extends DaoFactory{
	
	@Override
	public UserDAO getUserDao() {
		return new XmlUserDao();
	}

}
