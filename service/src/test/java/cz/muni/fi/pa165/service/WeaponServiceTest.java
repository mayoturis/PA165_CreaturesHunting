package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.UserDao;
import cz.muni.fi.pa165.dao.WeaponDao;
import cz.muni.fi.pa165.entities.User;
import cz.muni.fi.pa165.entities.Weapon;
import cz.muni.fi.pa165.service.exception.PersistenceException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.verification.VerificationMode;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * @author Marek Turis
 */
@Transactional
@RunWith(MockitoJUnitRunner.class)
public class WeaponServiceTest {

	private static VerificationMode once = times(1);

	WeaponService weaponService;

	@Mock
	Weapon weapon;

	@Mock
	User user;

	@Mock
	WeaponDao weaponDao;

	@Mock
	UserDao userDao;

	@Before
	public void setUp() {
		weaponService = new WeaponServiceImpl(weaponDao, userDao);
	}

	@Test
	public void createCallsDaoAndReturnsId() {
		int expectedId = 5;
		when(weapon.getId()).thenReturn(expectedId);

		int actualId = weaponService.create(weapon);

		verify(weaponDao, once).create(weapon);
		Assert.assertEquals(expectedId, actualId);
	}

	@Test(expected = PersistenceException.class)
	public void createThrowsPersistanceIncaptionInCaseOfDaoException() {
		doThrow(RuntimeException.class).when(weaponDao).create(weapon);

		weaponService.create(weapon);
	}

	@Test
	public void updateCallsDao() {
		weaponService.update(weapon);

		verify(weaponDao, once).update(weapon);
	}

	@Test(expected = PersistenceException.class)
	public void updateThrowsPersistanceIncaptionInCaseOfDaoException() {
		doThrow(RuntimeException.class).when(weaponDao).update(weapon);

		weaponService.update(weapon);
	}

	@Test
	public void deleteCallsDao() {
		weaponService.delete(weapon);

		verify(weaponDao, once).delete(weapon);
	}

	@Test(expected = PersistenceException.class)
	public void deleteThrowsPersistanceIncaptionInCaseOfDaoException() {
		doThrow(RuntimeException.class).when(weaponDao).delete(weapon);

		weaponService.delete(weapon);
	}

	@Test
	public void findByIdCallsDao() {
		int id = 5;
		when(weaponDao.findById(id)).thenReturn(weapon);

		Weapon actualWeapon = weaponService.findById(id);

		Assert.assertEquals(weapon, actualWeapon);
	}

	@Test(expected = PersistenceException.class)
	public void findByIdThrowsPersistanceIncaptionInCaseOfDaoException() {
		int id = 5;
		doThrow(RuntimeException.class).when(weaponDao).findById(id);

		weaponService.findById(id);
	}

	@Test
	public void findAllCallsDao() {
		List<Weapon> weaponList = Arrays.asList(weapon);
		when(weaponDao.findAll()).thenReturn(weaponList);

		List<Weapon> actualWeaponList = weaponService.findAll();

		Assert.assertEquals(weaponList, actualWeaponList);
	}

	@Test(expected = PersistenceException.class)
	public void findAllThrowsPersistanceIncaptionInCaseOfDaoException() {
		doThrow(RuntimeException.class).when(weaponDao).findAll();

		weaponService.findAll();
	}

	@Test
	public void weaponCanBeAddedToUser() {
		int userId = 5;
		int weaponId = 6;
		when(user.getId()).thenReturn(userId);
		when(weapon.getId()).thenReturn(weaponId);
		when(userDao.findById(userId)).thenReturn(user);
		when(weaponDao.findById(weaponId)).thenReturn(weapon);

		weaponService.addWeaponToUser(weapon, user);

		verify(user, once).addWeapon(weapon);
	}

	@Test(expected = PersistenceException.class)
	public void addWeaponToUserThrowsPersitenceExceptionIfUserDaoTrhowsException() {
		int userId = 5;
		int weaponId = 6;
		when(user.getId()).thenReturn(userId);
		when(weapon.getId()).thenReturn(weaponId);
		when(weaponDao.findById(weaponId)).thenReturn(weapon);

		doThrow(RuntimeException.class).when(userDao).findById(userId);

		weaponService.addWeaponToUser(weapon, user);
	}

	@Test(expected = PersistenceException.class)
	public void addWeaponToUserThrowsPersitenceExceptionIfWeaponDaoTrhowsException() {
		int userId = 7;
		int weaponId = 6;
		when(user.getId()).thenReturn(userId);
		when(weapon.getId()).thenReturn(weaponId);
		when(userDao.findById(userId)).thenReturn(user);

		doThrow(RuntimeException.class).when(weaponDao).findById(weaponId);

		weaponService.addWeaponToUser(weapon, user);
	}
}
