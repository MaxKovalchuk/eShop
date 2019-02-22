package xmlUserDao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import dao.UserDAO;
import ua.itea.models.User;

public class XmlUserDao implements UserDAO{
	@Override
	public boolean createUser(User user) {
		XStream xs = new XStream(new StaxDriver());
		try {
			xs.toXML(user, new FileOutputStream("user.xml"));
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public User getUser(int id) {
		User user = null;
		XStream xs = new XStream(new DomDriver());
		try {
			user = new User();
			xs.fromXML(new FileInputStream("user.xml"), user);
			return user;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}
