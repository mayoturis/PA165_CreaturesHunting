package cz.muni.fi.pa165.service.services;

import cz.muni.fi.pa165.dao.UserDao;
import cz.muni.fi.pa165.entities.User;
import cz.muni.fi.pa165.service.services.base.CrudServiceImpl;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Implementation for user service.
 *
 * @author Marek Turis
 */
@Named
public class UserServiceImpl extends CrudServiceImpl<User> implements UserService {

	@Inject
	public UserServiceImpl(UserDao userDao) {
		super(userDao);
	}
}
