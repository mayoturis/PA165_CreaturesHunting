package cz.muni.fi.pa165.service.services;

import cz.muni.fi.pa165.dao.AreaDao;
import cz.muni.fi.pa165.entities.Area;
import cz.muni.fi.pa165.enums.DangerLevel;
import cz.muni.fi.pa165.service.exceptions.PersistenceException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.verification.VerificationMode;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.mockito.Mockito.times;

/**
 * Tests for area service implementation.
 *
 * @author Simona Kruppova
 */
@Transactional
@RunWith(MockitoJUnitRunner.class)
public class AreaServiceTest {

	private static final VerificationMode ONCE = times(1);

	@Mock
	private AreaDao areaDao;

	private AreaService areaService;

	private Area area =  new Area("area", DangerLevel.EASY, BigDecimal.ONE);;

	@Before
	public void setUp() {
		areaService = new AreaServiceImpl(areaDao);
	}

	@Test
	public void createAreaTest() {
		areaService.create(area);
		Mockito.verify(areaDao, ONCE).create(area);
	}

	@Test(expected = PersistenceException.class)
	public void createNullAreaTest() {
		Mockito.doThrow(
				new PersistenceException("Trying to create null object.")
		).when(areaDao).create(null);

		areaService.create(null);
	}
}
