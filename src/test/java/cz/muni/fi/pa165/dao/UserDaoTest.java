package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.ApplicationContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Simona Kruppova
 */

@ContextConfiguration(classes=ApplicationContextConfiguration.class)
public class UserDaoTest {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private UserDao userDao;
}
