package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.ApplicationContextConfiguration;
import cz.muni.fi.pa165.entities.User;
import cz.muni.fi.pa165.enums.Gender;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ValidationException;
import java.util.List;

/**
 * User dao tests.
 *
 * @author Simona Kruppova
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationContextConfiguration.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class UserDaoTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private UserDao userDao;

	@PersistenceContext
	private EntityManager em;

	private User user;
	private User user1;
	private User user2;

	private String userName = "Peter";
	private String userEmail = "peter@email.com";
	private String userPassword = "password";
	private Gender userGender = Gender.MALE;
	private boolean userIsAdmin = false;
	private int userAge = 18;


	@Before
	public void createUsers() {
		user = new User();
		user1 = new User();
		user2 = new User();

		user.setName(userName);
		user.setGender(userGender);
		user.setPassword(userPassword);
		user.setEmail(userEmail);
		user.setAdmin(userIsAdmin);
		user.setAge(userAge);

		user1.setName("Jakub");
		user1.setPassword("mypass");
		user1.setEmail("jakub@email.com");

		user2.setName("Janka");
		user2.setPassword("testpass");
		user2.setEmail("janka@email.com");

		em.persist(user);
		em.persist(user1);
		em.persist(user2);
	}

	@Test
	public void findAllUsersTest() {
		List<User> users = userDao.findAll();
		Assert.assertEquals(3, users.size());
	}

	@Test
	public void findUserByIdTest() {
		User foundUser = userDao.findById(user.getId());

		Assert.assertEquals(userName, foundUser.getName());
		Assert.assertEquals(userEmail, foundUser.getEmail());
		Assert.assertEquals(userPassword, foundUser.getPassword());
		Assert.assertEquals(userGender, foundUser.getGender());
		Assert.assertEquals(userIsAdmin, foundUser.getAdmin());
		Assert.assertEquals(userAge, foundUser.getAge());
	}

	@Test
	public void createUserTest() {
		User user3 = new User();
		user3.setName("testName");
		user3.setEmail("testEmail");
		user3.setPassword("pass");

		userDao.create(user3);

		User foundUser = userDao.findById(user3.getId());
		Assert.assertEquals(foundUser, user3);
	}

	@Test(expected = ValidationException.class)
	public void createUserWithInvalidAgeTest() {
		User user3 = new User();
		user3.setName("testName");
		user3.setEmail("testEmail");
		user3.setPassword("pass");
		user3.setAge(-1);

		userDao.create(user3);
	}

	@Test
	public void deleteUserTest() {
		userDao.delete(user);

		List<User> users = userDao.findAll();
		Assert.assertEquals(2, users.size());
	}
}
