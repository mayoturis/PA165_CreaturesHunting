package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.dao.base.CrudDao;
import cz.muni.fi.pa165.entities.User;

/**
 * Interface providing access to operations related to entities.
 *
 * @author Marek Turis
 */
public interface UserDao extends CrudDao<User> {
	public User findByEmail(String email);
}
