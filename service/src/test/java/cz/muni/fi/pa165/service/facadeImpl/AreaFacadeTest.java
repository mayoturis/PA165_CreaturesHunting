package cz.muni.fi.pa165.service.facadeImpl;

import cz.muni.fi.pa165.dto.AreaDTO;
import cz.muni.fi.pa165.entities.Area;
import cz.muni.fi.pa165.enums.DangerLevel;
import cz.muni.fi.pa165.service.services.AreaService;
import cz.muni.fi.pa165.service.services.mapping.MappingService;
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
 * Test for area facade implementation.
 *
 * @author Simona Kruppova
 */
@Transactional
@RunWith(MockitoJUnitRunner.class)
public class AreaFacadeTest {

	private static final VerificationMode ONCE = times(1);

	private AreaFacadeImpl areaFacade;

	@Mock
	private MappingService mappingService;

	@Mock
	private AreaService areaService;

	private String areaName = "area";
	private DangerLevel danger = DangerLevel.EASY;
	private BigDecimal size = BigDecimal.ONE;
	private AreaDTO areaDto = new AreaDTO(areaName, danger, size);
	private Area area =  new Area(areaName, danger, size);

	@Before
	public void setUp() {
		areaFacade = new AreaFacadeImpl(areaService, mappingService);
		Mockito.when(mappingService.map(areaDto, Area.class)).thenReturn(area);
		Mockito.when(mappingService.map(area, AreaDTO.class)).thenReturn(areaDto);
		area.setId(1);
		areaDto.setId(1);
	}

	@Test
	public void createTest() {
		Mockito.when(areaService.create(area)).thenReturn(area.getId());
		int createdId = areaFacade.create(areaDto);

		Assert.assertEquals(areaDto.getId(), createdId);
		Mockito.verify(areaService, ONCE).create(area);
	}

	@Test
	public void updateTest() {
		areaFacade.update(areaDto);

		Mockito.verify(areaService, ONCE).update(area);
	}

	@Test
	public void deleteTest() {
		areaFacade.delete(areaDto);

		Mockito.verify(areaService, ONCE).delete(area);
	}

	@Test
	public void deleteByIdTest() {
		Mockito.when(areaService.findById(area.getId())).thenReturn(area);

		areaFacade.delete(area.getId());

		Mockito.verify(areaService, ONCE).delete(area);
	}

	@Test(expected = IllegalArgumentException.class)
	public void deleteByIdAreaDoesNotExistTest() {
		Mockito.when(areaService.findById(area.getId())).thenReturn(null);

		areaFacade.delete(area.getId());
	}

	@Test
	public void findByIdTest() {
		Mockito.when(areaService.findById(area.getId())).thenReturn(area);
		AreaDTO foundAreaDTO = areaFacade.findById(areaDto.getId());

		Assert.assertEquals(areaDto, foundAreaDTO);
		Mockito.verify(areaService, ONCE).findById(area.getId());
	}

	@Test
	public void findAllTest() {
		List<Area> areas = new ArrayList<Area>();
		List<AreaDTO> areaDtos = new ArrayList<AreaDTO>();
		areas.add(area);
		areaDtos.add(areaDto);

		Mockito.when(mappingService.map(areas, AreaDTO.class)).thenReturn(areaDtos);
		Mockito.when(areaService.findAll()).thenReturn(areas);
		List<AreaDTO> foundAreas = areaFacade.findAll();

		Assert.assertEquals(areaDtos, foundAreas);
		Mockito.verify(areaService, ONCE).findAll();
	}
}
