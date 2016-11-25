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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.validation.ValidationException;
import java.util.List;

/**
 * User dao tests.
 *
 * @author Simona Kruppova
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationContextConfiguration.class)
@Transactional
public class UserDaoTest {

	@Autowired
	private UserDao userDao;

	@PersistenceContext
	private EntityManager em;

	private User user;

	private String userName = "Peter";
	private String userEmail = "peter@email.com";
	private String userPassword = "password";
	private Gender userGender = Gender.MALE;
	private boolean userIsAdmin = false;
	private int userAge = 18;

	@Before
	public void createUsers() {
		user = new User();
		User user1 = new User();
		User user2 = new User();

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
		User user = GetValidUser();

		userDao.create(user);

		User foundUser = userDao.findById(user.getId());
		Assert.assertEquals(foundUser, user);
	}

	@Test(expected = ValidationException.class)
	public void createUserWithInvalidAgeTest() {
		User user = GetValidUser();
		user.setAge(-1);

		userDao.create(user);
	}

	@Test(expected = ValidationException.class)
	public void createUserWithNullEmailTest() {
		User user = GetValidUser();
		user.setEmail(null);

		userDao.create(user);
	}

	@Test(expected = ValidationException.class)
	public void createUserWithNullPasswordTest() {
		User user = GetValidUser();
		user.setPassword(null);

		userDao.create(user);
	}

	@Test(expected = NullPointerException.class)
	public void createUserWithNullNameTest() {
		User user = GetValidUser();
		user.setName(null);

		userDao.create(user);
	}

	@Test(expected = PersistenceException.class)
	public void createUserWithNameNotUniqueTest() {
		User user = GetValidUser();
		User user1 = GetValidUser();
		user1.setEmail("testEmail1");

		userDao.create(user);
		userDao.create(user1);
	}

	@Test(expected = PersistenceException.class)
	public void createUserWithEmailNotUniqueTest() {
		User user = GetValidUser();
		User user1 = GetValidUser();
		user1.setName("testName1");

		userDao.create(user);
		userDao.create(user1);
	}

	@Test
	public void updateUserTest() {
		String newUserName = "newName";
		user.setName(newUserName);
		userDao.update(user);

		User foundUser = userDao.findById(user.getId());

		Assert.assertEquals(newUserName, foundUser.getName());
		Assert.assertEquals(userEmail, foundUser.getEmail());
	}

	@Test
	public void deleteUserTest() {
		userDao.delete(user);

		List<User> users = userDao.findAll();
		Assert.assertEquals(2, users.size());
	}

	private User GetValidUser() {

		User user = new User();

		user.setName("testName");
		user.setEmail("testEmail");
		user.setPassword("pass");

		return user;
	}
}
