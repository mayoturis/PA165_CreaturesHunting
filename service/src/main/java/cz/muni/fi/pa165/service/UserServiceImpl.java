package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.UserDao;
import cz.muni.fi.pa165.entities.User;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author Marek Turis
 */
@Named
public class UserServiceImpl extends CrudServiceImpl<User> implements UserService {

	@Inject
	public UserServiceImpl(UserDao userDao) {
		super(userDao);
	}
}
