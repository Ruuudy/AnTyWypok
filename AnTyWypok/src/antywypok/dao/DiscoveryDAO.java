package antywypok.dao;

import java.util.List;

import antywypok.model.Discovery;

public interface DiscoveryDAO extends GenericDAO<Discovery,Long> {
	
	List<Discovery> getAll();
}
