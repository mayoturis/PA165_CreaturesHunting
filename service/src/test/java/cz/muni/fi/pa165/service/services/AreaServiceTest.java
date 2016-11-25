package cz.muni.fi.pa165.service.services;

import cz.muni.fi.pa165.dao.AreaDao;
import cz.muni.fi.pa165.entities.Area;
import cz.muni.fi.pa165.enums.DangerLevel;
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

	private Area area =  new Area("area", DangerLevel.EASY, BigDecimal.ONE);

	@Before
	public void setUp() {
		areaService = new AreaServiceImpl(areaDao);
		area.setId(1);
	}

	@Test
	public void createTest() {
		int createdAreaId = areaService.create(area);
		Mockito.verify(areaDao, ONCE).create(area);
		Assert.assertEquals(area.getId(), createdAreaId);
	}

	@Test(expected = HuntingPersistenceException.class)
	public void createWrapsRuntimeExceptionToPersistenceExceptionTest() {
		Mockito.doThrow(RuntimeException.class).when(areaDao).create(area);
		areaService.create(area);
	}

	@Test
	public void updateTest() {
		areaService.update(area);
		Mockito.verify(areaDao, ONCE).update(area);
	}

	@Test(expected = HuntingPersistenceException.class)
	public void updateWrapsRuntimeExceptionToPersistenceExceptionTest() {
		Mockito.doThrow(RuntimeException.class).when(areaDao).update(area);
		areaService.update(area);
	}

	@Test
	public void deleteTest() {
		areaService.delete(area);
		Mockito.verify(areaDao, ONCE).delete(area);
	}

	@Test(expected = HuntingPersistenceException.class)
	public void deleteWrapsRuntimeExceptionToPersistenceExceptionTest() {
		Mockito.doThrow(RuntimeException.class).when(areaDao).delete(area);
		areaService.delete(area);
	}

	@Test
	public void findByIdTest() {
		Mockito.when(areaService.findById(area.getId())).thenReturn(area);
		Area foundArea = areaService.findById(area.getId());

		Assert.assertEquals(area, foundArea);
		Mockito.verify(areaDao, ONCE).findById(area.getId());
	}

	@Test(expected = HuntingPersistenceException.class)
	public void findByIdWrapsRuntimeExceptionToPersistenceExceptionTest() {
		Mockito.doThrow(RuntimeException.class).when(areaDao).findById(area.getId());
		areaService.findById(area.getId());
	}

	@Test
	public void findAllTest() {
		List<Area> areaList = new ArrayList<Area>();
		areaList.add(area);
		Mockito.when(areaService.findAll()).thenReturn(areaList);
		List<Area> foundAreaList = areaService.findAll();

		Assert.assertEquals(areaList, foundAreaList);
		Mockito.verify(areaDao, ONCE).findAll();
	}

	@Test(expected = HuntingPersistenceException.class)
	public void findAllWrapsRuntimeExceptionToPersistenceExceptionTest() {
		Mockito.doThrow(RuntimeException.class).when(areaDao).findAll();
		areaService.findAll();
	}
}
