package cz.muni.fi.pa165.service.facadeImpl;

import cz.muni.fi.pa165.dto.WeaponDTO;
import cz.muni.fi.pa165.entities.Weapon;
import cz.muni.fi.pa165.service.services.WeaponService;
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

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

/**
 * @author Marek Turis
 */
@RunWith(MockitoJUnitRunner.class)
public class WeaponFacadeTest {
	private static VerificationMode once = times(1);

	private WeaponFacadeImpl weaponFacade;

	@Mock
	private Weapon weapon;

	@Mock
	private WeaponDTO weaponDTO;

	@Mock
	private WeaponService weaponService;

	@Mock
	private MappingService mappingService;

	@Before
	public void setUp() {
		weaponFacade = new WeaponFacadeImpl(weaponService, mappingService);
	}

	@Test
	public void createCallsServiceAndReturnsId() {
		int expectedId = 5;
		when(mappingService.map(weaponDTO, Weapon.class)).thenReturn(weapon);
		when(weaponService.create(weapon)).thenReturn(expectedId);

		int actualId = weaponFacade.create(weaponDTO);

		verify(weaponService, once).create(weapon);
		Assert.assertEquals(expectedId, actualId);
	}

	@Test
	public void updateCallsService() {
		when(mappingService.map(weaponDTO, Weapon.class)).thenReturn(weapon);

		weaponFacade.update(weaponDTO);

		verify(weaponService, once).update(weapon);
	}

	@Test
	public void deleteCallsService() {
		when(mappingService.map(weaponDTO, Weapon.class)).thenReturn(weapon);

		weaponFacade.delete(weaponDTO);

		verify(weaponService, once).delete(weapon);
	}

	@Test
	public void deleteByIdFindsAndDeletesWeapon() {
		int weaponId = 5;
		when(weaponService.findById(weaponId)).thenReturn(weapon);

		weaponFacade.delete(weaponId);

		verify(weaponService, once).delete(weapon);
	}

	@Test(expected = IllegalArgumentException.class)
	public void deleteByIdThrowsExceptionIfNotFound() {
		int weaponId = 5;
		when(weaponService.findById(weaponId)).thenReturn(null);

		weaponFacade.delete(weaponId);
	}

	@Test
	public void findByIdCallsService() {
		int weaponId = 5;
		when(weaponService.findById(weaponId)).thenReturn(weapon);
		when(mappingService.map(weapon, WeaponDTO.class)).thenReturn(weaponDTO);

		WeaponDTO actualWeapon = weaponFacade.findById(weaponId);

		verify(weaponService, once).findById(weaponId);
		Assert.assertEquals(weaponDTO, actualWeapon);
	}

	@Test
	public void getWeaponByName() {
		String name = "luk";
		when(weaponService.getWeaponByName(name)).thenReturn(weapon);
		when(mappingService.map(weapon, WeaponDTO.class)).thenReturn(weaponDTO);

		WeaponDTO actualWeapon = weaponFacade.getWeaponByName(name);

		verify(weaponService, once).getWeaponByName(name);
		Assert.assertEquals(weaponDTO, actualWeapon);
	}

	@Test
	public void findAllCallsService() {
		List<Weapon> weaponList = Arrays.asList(weapon);
		List<WeaponDTO> weaponDTOList = Arrays.asList(weaponDTO);
		when(weaponService.findAll()).thenReturn(weaponList);
		when(mappingService.map(weaponList, WeaponDTO.class)).thenReturn(weaponDTOList);

		List<WeaponDTO> actualWeaponDTOList = weaponFacade.findAll();

		Assert.assertEquals(weaponDTOList, actualWeaponDTOList);
	}
}
