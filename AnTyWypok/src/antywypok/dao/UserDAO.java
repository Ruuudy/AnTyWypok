package antywypok.dao;

import java.util.List;

import antywypok.model.User;

public interface UserDAO extends GenericDAO<User, Long>{
	List<User> getAll();
	User getUserByUsername(String username);
}
