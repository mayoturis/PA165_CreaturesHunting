package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.ApplicationContextConfiguration;
import cz.muni.fi.pa165.entities.Monster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import javax.validation.ValidationException;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Marek Turis
 */
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(classes=ApplicationContextConfiguration.class)
@Transactional
public class MonsterDaoTest {

	@Autowired
	public MonsterDao monsterDao;

	@Test
	public void findAllAndCreate() {
		Monster monster1 = getValidMonster1();
		Monster monster2 = getValidMonster2();

		monsterDao.create(monster1);
		monsterDao.create(monster2);

		List<Monster> monsters = monsterDao.findAll();

		Assert.assertEquals(2, monsters.size());
		Assert.assertTrue(monster1.equals(monsters.get(0)));
		Assert.assertTrue(monster2.equals(monsters.get(1)));
	}

	@Test(expected=ValidationException.class)
	public void monsterWithNegativeHeightCannotBeAdded() {
		Monster monster = getMonster("troll", -6, 6, 6, 6);

		monsterDao.create(monster);
	}

	@Test(expected=ValidationException.class)
	public void monsterWithNegativeWeightCannotBeAdded() {
		Monster monster = getMonster("troll", 6, -6, 6, 6);

		monsterDao.create(monster);
	}

	@Test(expected=ValidationException.class)
	public void monsterWithAgilityOutOfRangeCannotBeAdded() {
		Monster monster = getMonster("troll", 6, 6, 20, 6);

		monsterDao.create(monster);
	}

	@Test(expected=ValidationException.class)
	public void monsterWithStrengthOutOfRangeCannotBeAdded() {
		Monster monster = getMonster("troll", 6, 6, 6, 20);

		monsterDao.create(monster);
	}

	@Test
	public void delete() {
		Monster monster1 = getValidMonster1();
		Monster monster2 = getValidMonster2();

		monsterDao.create(monster1);
		monsterDao.create(monster2);
		monsterDao.delete(monster1);

		List<Monster> monsters = monsterDao.findAll();

		Assert.assertEquals(1, monsters.size());
		Assert.assertTrue(monster2.equals(monsters.get(0)));
	}

	@Test
	public void findById() {
		Monster monster = getValidMonster1();
		monsterDao.create(monster);

		Monster createdMonster = monsterDao.findById(monster.getId());

		Assert.assertTrue(monster.equals(createdMonster));
		Assert.assertEquals(monster.getAgility(), createdMonster.getAgility());
		Assert.assertEquals(monster.getStrength(), createdMonster.getStrength());
		Assert.assertEquals(monster.getHeight(), createdMonster.getHeight());
		Assert.assertEquals(monster.getWeight(), createdMonster.getWeight());
		Assert.assertEquals(monster.getType(), createdMonster.getType());
	}

	@Test
	public void findByName() {
		Monster monster1 = getValidMonster1();
		Monster monster2 = getValidMonster2();

		monsterDao.create(monster1);
		monsterDao.create(monster2);

		Monster foundMonster = monsterDao.findByType(monster1.getType());

		Assert.assertTrue(monster1.equals(foundMonster));
	}

	@Test(expected = PersistenceException.class)
	public void twoMonstersWithSameTypeCannotBeCreated() {
		Monster monster1 = getValidMonster1();
		Monster monster2 = getMonster(monster1.getType(), 5, 5, 5, 5);

		monsterDao.create(monster1);
		monsterDao.create(monster2);
	}

	private Monster getValidMonster1() {
		return getMonster("troll", 5, 5, 5, 5);
	}

	private Monster getValidMonster2() {
		return getMonster("zombie", 6, 6, 6, 6);
	}

	private Monster getMonster(String type, int height, int weight, int agility, int strength) {
		Monster monster = new Monster();
		monster.setType(type);
		monster.setHeight(new BigDecimal(height));
		monster.setWeight(new BigDecimal(weight));
		monster.setAgility(agility);
		monster.setStrength(strength);
		return monster;
	}
}
