package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.WeaponDTO;
import cz.muni.fi.pa165.entities.Weapon;
import cz.muni.fi.pa165.facade.WeaponFacade;
import cz.muni.fi.pa165.service.MappingService;
import cz.muni.fi.pa165.service.WeaponService;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author Ondrej Zeman
 */
@Named
public class WeaponFacadeImpl extends CrudFacadeImpl<WeaponDTO, Weapon> implements WeaponFacade {

	@Inject
	public WeaponFacadeImpl(WeaponService crudService, MappingService mappingService) {
		super(crudService, mappingService, WeaponDTO.class, Weapon.class);
	}
}
