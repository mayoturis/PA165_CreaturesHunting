package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.ApplicationContextConfiguration;
import cz.muni.fi.pa165.entities.Area;
import cz.muni.fi.pa165.enums.DangerLevel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Ondrej Zeman
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = ApplicationContextConfiguration.class)
public class AreaDaoTest {

	final static Logger logger = Logger.getLogger(AreaDaoTest.class.toString());
	Area area;
	Area area2;
	Area area3;
	@PersistenceContext
	private EntityManager em;
	@Inject
	private AreaDao areaDao;

	@Before
	public void createAreas() {
		area = new Area();
		area.setName("Kappa");
		area.setDangerLevel(DangerLevel.EASY);
		area.setSize(BigDecimal.TEN);


		area2 = new Area();
		area2.setName("area2");
		area2.setDangerLevel(DangerLevel.HARD);
		area2.setSize(BigDecimal.ONE);


		area3 = new Area();
		area3.setName("TwitchChat");
		area3.setDangerLevel(DangerLevel.EXTREME);
		area3.setSize(new BigDecimal(100));

		em.persist(area);
		em.persist(area2);
		em.persist(area3);
	}

	@Test
	public void createAreaTest() {
		Area area1 = new Area();
		area1.setName("test");
		area1.setDangerLevel(DangerLevel.EXTREME);
		area1.setSize(BigDecimal.TEN);
		areaDao.create(area1);
		Assert.assertEquals(areaDao.findById(area1.getId()), area1);
	}

	@Test
	public void findAllTest() {
		List<Area> areas = areaDao.findAll();
		Assert.assertEquals(3, areas.size());
		Assert.assertEquals(area.getName(), areas.get(0).getName());
		Assert.assertEquals(area2.getName(), areas.get(1).getName());
		Assert.assertEquals(area3.getName(), areas.get(2).getName());


	}

	@Test
	public void findByIdTest() {
		Area found = areaDao.findById(area.getId());
		Assert.assertEquals(area.getName(), found.getName());
		Assert.assertEquals(area.getDangerLevel(), found.getDangerLevel());
		Assert.assertEquals(area.getSize(), found.getSize());
	}

	@Test
	public void remove() {
		Assert.assertNotNull(areaDao.findById(area.getId()));
		areaDao.delete(area);
		Assert.assertNull(areaDao.findById(area.getId()));
	}

	@Test
	public void idNotInDatabase() {
		Area areaNull = areaDao.findById(Integer.MAX_VALUE);
		Assert.assertEquals(null, areaNull);
	}

	@Test(expected = IllegalArgumentException.class)
	public void removeNonexistent() {
		Area nonExistentArea = new Area();
		nonExistentArea.setSize(BigDecimal.ZERO);
		nonExistentArea.setDangerLevel(DangerLevel.HARD);
		nonExistentArea.setDescription("nonExistentArea");
		nonExistentArea.setName("nonExistentArea");
		areaDao.delete(nonExistentArea);
	}


	@Test(expected = ConstraintViolationException.class)
	public void createBadInputTest() {
		Area badArea = new Area();
		badArea.setName("BadArea");
		areaDao.create(badArea);

	}

	@Test(expected = ValidationException.class)
	public void createBadSizeTest() {
		Area badArea = new Area();
		badArea.setSize(BigDecimal.ZERO);
		badArea.setDangerLevel(DangerLevel.HARD);
		badArea.setDescription("BadSizeArea");
		badArea.setName("BadArea");
		areaDao.create(badArea);
	}

	@Test(expected = PersistenceException.class)
	public void createAreasWithSameName() {
		Area a1 = new Area();
		a1.setName("Kappa");
		a1.setDangerLevel(DangerLevel.EASY);
		a1.setSize(BigDecimal.TEN);

		Area a2 = new Area();
		a2.setName("Kappa");
		a2.setDangerLevel(DangerLevel.EASY);
		a2.setSize(BigDecimal.TEN);
		areaDao.create(a1);
		areaDao.create(a2);
	}

	@Test
	public void updateArea() {
		area.setName("Mordor");
		areaDao.update(area);
		Assert.assertEquals(area.getId(), areaDao.findById(area.getId()).getId());


	}

	@Test(expected = ConstraintViolationException.class)
	public void updateEntityDoesntExist() {
		Area a = new Area();
		a.setName("Riverrun");
		areaDao.update(a);

	}

}
