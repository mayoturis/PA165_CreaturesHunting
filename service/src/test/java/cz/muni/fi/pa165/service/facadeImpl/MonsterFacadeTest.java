package cz.muni.fi.pa165.service.facadeImpl;

import cz.muni.fi.pa165.dto.MonsterDTO;
import cz.muni.fi.pa165.entities.Monster;
import cz.muni.fi.pa165.facade.MonsterFacade;
import cz.muni.fi.pa165.service.services.MonsterService;
import cz.muni.fi.pa165.service.services.mapping.MappingService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.verification.VerificationMode;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for MonsterFacadeImpl - monster facade implementation
 *
 * @author Michael Cada
 */
@RunWith(MockitoJUnitRunner.class)
public class MonsterFacadeTest {
	private static VerificationMode once = times(1);

	private MonsterFacade monsterFacade;

	@Mock
	private Monster monster;
	@Mock
	private MonsterDTO monsterDTO;

	@Mock
	private MonsterService monsterService;

	@Mock
	private MappingService mappingService;

	@Before
	public void setUp() {
		monsterFacade = new MonsterFacadeImpl(monsterService, mappingService);
	}

	@Test
	public void deleteCallsServiceTest() {
		when(mappingService.map(monsterDTO, Monster.class)).thenReturn(monster);

		monsterFacade.delete(monsterDTO);

		verify(monsterService, once).delete(monster);
	}

	@Test
	public void deleteByIdFindsAndDeletesMonsterTest() {
		int monsterId = 666;
		when(monsterService.findById(monsterId)).thenReturn(monster);

		monsterFacade.delete(monsterId);

		verify(monsterService, once).delete(monster);
	}

	@Test(expected = IllegalArgumentException.class)
	public void deleteByIdThrowsExceptionIfNotFoundTest() {
		int monsterId = 666;
		when(monsterService.findById(monsterId)).thenReturn(null);

		monsterFacade.delete(monsterId);
	}

	@Test
	public void findByIdCallsServiceTest() {
		int monsterId = 666;
		when(monsterService.findById(monsterId)).thenReturn(monster);
		when(mappingService.map(monster, MonsterDTO.class)).thenReturn(monsterDTO);

		MonsterDTO actualMonster = monsterFacade.findById(monsterId);

		verify(monsterService, once).findById(monsterId);
		Assert.assertEquals(monsterDTO, actualMonster);
	}

	@Test
	public void findAllCallsServiceTest() {
		List<Monster> monsterList = Arrays.asList(monster);
		List<MonsterDTO> monsterDTOList = Arrays.asList(monsterDTO);
		when(monsterService.findAll()).thenReturn(monsterList);
		when(mappingService.map(monsterList, MonsterDTO.class)).thenReturn(monsterDTOList);

		List<MonsterDTO> actualMonsterDTOList = monsterFacade.findAll();

		Assert.assertEquals(monsterDTOList, actualMonsterDTOList);
	}

	@Test
	public void createCallsServiceAndReturnsIdTest() {
		int expectedId = 666;
		when(mappingService.map(monsterDTO, Monster.class)).thenReturn(monster);
		when(monsterService.create(monster)).thenReturn(expectedId);

		int actualId = monsterFacade.create(monsterDTO);

		verify(monsterService, once).create(monster);
		Assert.assertEquals(expectedId, actualId);
	}

	@Test
	public void updateCallsServiceTest() {
		when(mappingService.map(monsterDTO, Monster.class)).thenReturn(monster);

		monsterFacade.update(monsterDTO);

		verify(monsterService, once).update(monster);
	}
}
