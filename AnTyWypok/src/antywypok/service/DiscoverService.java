package antywypok.service;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

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
	
	public List<Discovery> getAllDiscoveries(){
		return getAllDiscoveries(null);
	}
	
	public List<Discovery> getAllDiscoveries(Comparator<Discovery> comparator){
		DAOFactory factory = DAOFactory.getDAOFactory();
		DiscoveryDAO discoveryDAO = factory.getDiscoveryDAO();
		List<Discovery> discoveries = discoveryDAO.getAll();
		if(comparator != null && discoveries != null){
			discoveries.sort(comparator);
		}
		return discoveries;
	}
}
