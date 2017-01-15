package cz.muni.fi.pa165.service.services;

import cz.muni.fi.pa165.dao.MonsterDao;
import cz.muni.fi.pa165.dao.WeaponDao;
import cz.muni.fi.pa165.entities.Area;
import cz.muni.fi.pa165.entities.Monster;
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

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Tests for MonsterService
 *
 * @author Michael Cada
 */
@Transactional
@RunWith(MockitoJUnitRunner.class)
public class MonsterServiceTest {

	private static VerificationMode once = times(1);

	private MonsterService monsterService;

	@Mock
	private MonsterDao monsterDao;

	@Mock
	private WeaponDao weaponDao;

	@Mock
	private Monster monster;

	@Mock
	private Area area;

	@Before
	public void setup() {
		monsterService = new MonsterServiceImpl(monsterDao, weaponDao);
	}

	@Test
	public void createTest() {

		Mockito.when(monster.getId()).thenReturn(666);
		int actual = monsterService.create(monster);
		Assert.assertEquals(666, actual);
		Mockito.verify(monsterDao, once).create(monster);
	}

	@Test
	public void findByIdCallsDaoTest() {
		int id = 666;
		when(monsterDao.findById(id)).thenReturn(monster);

		Monster actualMonster = monsterService.findById(id);

		Assert.assertEquals(monster, actualMonster);
	}

	@Test(expected = HuntingPersistenceException.class)
	public void findByIdRuntimeExcTest() {
		int id = 666;
		doThrow(RuntimeException.class).when(monsterDao).findById(id);

		monsterService.findById(id);
	}

	@Test
	public void findAllCallsDaoTest() {
		List<Monster> monsterList = Arrays.asList(monster);
		when(monsterDao.findAll()).thenReturn(monsterList);

		List<Monster> actualMonsterList = monsterService.findAll();

		Assert.assertEquals(monsterList, actualMonsterList);
	}

	@Test(expected = HuntingPersistenceException.class)
	public void findAllThrowsPersistanceExcInCaseOfDaoExceptionTest() {
		doThrow(RuntimeException.class).when(monsterDao).findAll();

		monsterService.findAll();
	}

	@Test(expected = HuntingPersistenceException.class)
	public void createWithDatabaseProblemTest() {
		Mockito.doThrow(RuntimeException.class).when(monsterDao).create(monster);

		monsterService.create(monster);
	}

	@Test
	public void updateCallsDaoTest() {
		monsterService.update(monster);

		verify(monsterDao, once).update(monster);
	}

	@Test(expected = HuntingPersistenceException.class)
	public void updateRuntimeExcTest() {
		doThrow(RuntimeException.class).when(monsterDao).update(monster);

		monsterService.update(monster);
	}

	@Test
	public void deleteCallsDaoTest() {
		monsterService.delete(monster);

		verify(monsterDao, once).delete(monster);
	}

	@Test(expected = HuntingPersistenceException.class)
	public void deleteThrowsPersistanceIncaptionInCaseOfDaoExceptionTest() {
		doThrow(RuntimeException.class).when(monsterDao).delete(monster);

		monsterService.delete(monster);
	}

}
