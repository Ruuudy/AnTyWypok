package antywypok.service;

import java.sql.Timestamp;
import java.util.Date;

import antywypok.dao.DAOFactory;
import antywypok.dao.DiscoveryDAO;
import antywypok.model.Discovery;
import antywypok.model.User;

public class DiscoverService {
	public void addDiscovery(String name, String desc, String url, User user){
		Discovery discovery = createDiscoveryObject(name, desc, url, user);
		DAOFactory factory = DAOFactory.getDAOFactory();
		DiscoveryDAO discoveryDao = factory.getDiscoveryDAO();
		discoveryDao.create(discovery);
	}
	
	private Discovery createDiscoveryObject(String name,String desc,String url,User user){
		Discovery discovery = new Discovery();
		discovery.setName(name);
		discovery.setDescription(desc);
		discovery.setUrl(url);
		User copy = new User(user);
		discovery.setUser(copy);
		discovery.setTimestamp(new Timestamp(new Date().getTime()));
		return discovery;
	}
}
