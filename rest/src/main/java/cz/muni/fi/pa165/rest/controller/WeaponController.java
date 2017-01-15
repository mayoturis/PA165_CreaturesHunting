package cz.muni.fi.pa165.rest.controller;


import cz.muni.fi.pa165.dto.WeaponDTO;
import cz.muni.fi.pa165.facade.WeaponFacade;
import cz.muni.fi.pa165.rest.exception.RestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;
import java.util.function.Function;

/**
 * @author Ondrej Zeman
 */

@RestController
@RequestMapping("/weapon")
public class WeaponController {

	private final WeaponFacade weaponFacade;

	@Inject
	public WeaponController(WeaponFacade weaponFacade) {
		this.weaponFacade = weaponFacade;
	}

	/**
	 * Returns all weapons as JSON.
	 *
	 * @return List of WeaponDtos as JSON
	 */
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public final List<WeaponDTO> getWeapons() {
		return weaponFacade.findAll();
	}

	/**
	 * Returns Weapon with the given id as JSON.
	 *
	 * @param id ID of the weapon to return
	 * @return WeaponDto as JSON
	 * @throws RestException Thrown when weapon with given ID not found
	 */
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public final WeaponDTO getWeapon(@PathVariable("id") int id) throws RestException {
		return resolveNotFound(weaponFacade.findById(id));
	}

	/**
	 * Creates a weapon with the given information.
	 *
	 * @param weapon WeaponDto
	 * @return Created weapon
	 * @throws RestException Thrown when resource already exists
	 */
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public final WeaponDTO createWeapon(@RequestBody WeaponDTO weapon) throws RestException {
		int id = weaponFacade.create(weapon);
		return weaponFacade.findById(id);
	}

	/**
	 * Updates weapon with the given ID.
	 *
	 * @param weapon Weapon with the updated information
	 * @param id     ID of the weapon to update
	 * @return Updated weapon
	 * @throws RestException Thrown when resource already exists
	 */
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public final WeaponDTO updateWeapon(@RequestBody WeaponDTO weapon,
										@PathVariable("id") int id) throws RestException {

		weapon.setId(id);
		weaponFacade.update(weapon);
		return weaponFacade.findById(weapon.getId());

	}

	/**
	 * Deletes a weapon with the given ID.
	 *
	 * @param id ID of the weapon to delete.
	 */
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public final void deleteWeapon(@PathVariable("id") int id) {
		weaponFacade.delete(id);
	}

	/**
	 * Throws ResourceNotFoundException if object is null. Otherwise returns object
	 *
	 * @param object Object to check if null
	 * @param <T>    Type of the object
	 * @return Given object if it's not null
	 */
	private <T> T resolveNotFound(T object) {
		if (object == null)
			throw new RestException();
		return object;
	}

	/**
	 * Throws ResourceAlreadyExistsException if SportsClubServiceException is thrown whe function is applied
	 *
	 * @param object   Object to process
	 * @param function Function to apply
	 */
	private WeaponDTO resolveAlreadyExist(WeaponDTO object, Function<WeaponDTO, WeaponDTO> function) {

		return function.apply(object);
	}
}