package cz.muni.fi.pa165.service.facadeImpl;

import cz.muni.fi.pa165.dto.WeaponDTO;
import cz.muni.fi.pa165.entities.Weapon;
import cz.muni.fi.pa165.facade.WeaponFacade;
import cz.muni.fi.pa165.service.facadeImpl.base.CrudFacadeImpl;
import cz.muni.fi.pa165.service.services.mapping.MappingService;
import cz.muni.fi.pa165.service.services.WeaponService;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Weapon facade implementation.
 *
 * @author Ondrej Zeman
 */
@Named
@Transactional
public class WeaponFacadeImpl extends CrudFacadeImpl<WeaponDTO, Weapon> implements WeaponFacade {

	@Inject
	public WeaponFacadeImpl(WeaponService weaponService, MappingService mappingService) {
		super(weaponService, mappingService, WeaponDTO.class, Weapon.class);
	}
}
