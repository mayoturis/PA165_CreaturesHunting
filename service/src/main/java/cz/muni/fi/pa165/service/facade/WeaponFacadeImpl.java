package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.UserDTO;
import cz.muni.fi.pa165.dto.WeaponDTO;
import cz.muni.fi.pa165.entities.User;
import cz.muni.fi.pa165.entities.Weapon;
import cz.muni.fi.pa165.facade.WeaponFacade;
import cz.muni.fi.pa165.service.MappingService;
import cz.muni.fi.pa165.service.WeaponService;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author Ondrej Zeman
 */
@Named
@Transactional
public class WeaponFacadeImpl extends CrudFacadeImpl<WeaponDTO, Weapon> implements WeaponFacade {

	@Inject
	private WeaponService weaponService;

	@Inject
	private MappingService mappingService;

	@Inject
	public WeaponFacadeImpl(WeaponService crudService, MappingService mappingService) {
		super(crudService, mappingService, WeaponDTO.class, Weapon.class);
	}

	@Override
	public void addWeaponToUser(WeaponDTO weapon, UserDTO user) {

		if(user!=null||weapon!=null){
			weaponService.addWeaponToUser(
					mappingService.map(weapon,Weapon.class)
					,mappingService.map(user, User.class));
		}

	}
}
