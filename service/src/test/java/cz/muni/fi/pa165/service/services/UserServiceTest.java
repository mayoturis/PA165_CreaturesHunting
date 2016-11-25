package cz.muni.fi.pa165.service.services;

import cz.muni.fi.pa165.dao.UserDao;
import cz.muni.fi.pa165.dao.WeaponDao;
import cz.muni.fi.pa165.entities.User;
import cz.muni.fi.pa165.entities.Weapon;
import cz.muni.fi.pa165.service.exceptions.HuntingPersistenceException;
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

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doThrow;

/**
 * Tests for UserService
 *
 * @author Ondrej Zeman
 */
@Transactional
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	private static VerificationMode oneTime = times(1);

	private UserServiceImpl userService;

	@Mock
	private UserDao userDao;

	@Mock
	private WeaponDao weaponDao;

	@Mock
	private User user;

	@Mock
	private Weapon weapon;

	@Before
	public void setup() {
		userService = new UserServiceImpl(weaponDao, userDao);
	}

	@Test
	public void create() {

		Mockito.when(user.getId()).thenReturn(1337);
		int actual = userService.create(user);
		Assert.assertEquals(1337, actual);
		Mockito.verify(userDao, oneTime).create(user);
	}

	@Test(expected = HuntingPersistenceException.class)
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
		int id = 1337;
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

	@Test
	public void weaponCanBeAddedToUser() {
		int userId = 5;
		int weaponId = 6;
		when(user.getId()).thenReturn(userId);
		when(weapon.getId()).thenReturn(weaponId);
		when(userDao.findById(userId)).thenReturn(user);
		when(weaponDao.findById(weaponId)).thenReturn(weapon);

		userService.addWeaponToUser(weapon, user);

		verify(user, oneTime).addWeapon(weapon);
	}

	@Test(expected = HuntingPersistenceException.class)
	public void addWeaponToUserThrowsPersistenceExceptionIfUserDaoThrowsException() {
		int userId = 5;
		int weaponId = 6;
		when(user.getId()).thenReturn(userId);
		when(weapon.getId()).thenReturn(weaponId);
		when(weaponDao.findById(weaponId)).thenReturn(weapon);

		doThrow(RuntimeException.class).when(userDao).findById(userId);

		userService.addWeaponToUser(weapon, user);
	}

	@Test(expected = HuntingPersistenceException.class)
	public void addWeaponToUserThrowsPersistenceExceptionIfWeaponDaoThrowsException() {
		int userId = 7;
		int weaponId = 6;
		when(user.getId()).thenReturn(userId);
		when(weapon.getId()).thenReturn(weaponId);
		when(userDao.findById(userId)).thenReturn(user);

		doThrow(RuntimeException.class).when(weaponDao).findById(weaponId);

		userService.addWeaponToUser(weapon, user);
	}
}
