package cz.muni.fi.pa165.service.services;

import cz.muni.fi.pa165.dao.UserDao;
import cz.muni.fi.pa165.entities.User;
import cz.muni.fi.pa165.service.exceptions.PersistenceException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.verification.VerificationMode;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

/**
 * Tests for UserService
 *
 * @author Ondrej Zeman
 */
@Transactional
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	private static VerificationMode oneTime = times(1);

	private UserService userService;

	@Mock
	private UserDao userDao;

	@Mock
	private User user;


	@Before
	public void setup() {
		userService = new UserServiceImpl(userDao);
	}

	@Test
	public void create() {

		Mockito.when(user.getId()).thenReturn(5);
		int actual = userService.create(user);
		Assert.assertEquals(5, actual);
		Mockito.verify(userDao, oneTime).create(user);
	}

	@Test(expected = PersistenceException.class)
	public void createWithDatabaseProblem() {
		Mockito.doThrow(RuntimeException.class).when(userDao).create(user);

		userService.create(user);
	}

	@Test
	public void update() {
		userService.update(user);
		Mockito.verify(userDao, oneTime).update(user);
	}

	@Test
	public void delete() {
		userService.delete(user);

		Mockito.verify(userDao, oneTime).delete(user);
	}

	@Test
	public void findById() {
		int id = 5;
		Mockito.when(userDao.findById(id)).thenReturn(user);

		User actualEntity = userService.findById(id);

		Assert.assertEquals(user, actualEntity);
	}

	@Test
	public void findAll() {
		List<User> expected = Collections.singletonList(user);
		when(userDao.findAll()).thenReturn(expected);

		List<User> actual = userService.findAll();

		Assert.assertEquals(expected, actual);
	}

}
