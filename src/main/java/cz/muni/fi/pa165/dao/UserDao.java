package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entities.User;

import java.util.List;

/**
 * Created by Marek on 26.10.2016.
 */
public interface UserDao {
	List<User> findAll();
	User findById(int id);
	void create(User user);
	void delete(User user);
}
