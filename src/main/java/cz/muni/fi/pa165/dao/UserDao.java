package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entities.User;

import java.util.List;

/**
 * Created by Marek on 26.10.2016.
 */
public interface UserDao {
	/**
	 * Finds all users
	 *
	 * @return all users
	 */
	List<User> findAll();

	/**
	 *
	 * Finds user by id
	 *
	 * @param id Id of user
	 * @return found user
	 */
	User findById(int id);

	/**
	 * Creates new user
	 *
	 * @param user to create
	 */
	void create(User user);

	/**
	 * Deletes user
	 *
	 * @param user
	 */
	void delete(User user);
}
