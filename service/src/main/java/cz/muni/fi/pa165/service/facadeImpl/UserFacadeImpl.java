package cz.muni.fi.pa165.service.facadeImpl;

import cz.muni.fi.pa165.dto.UserAuthenticationDTO;
import cz.muni.fi.pa165.dto.UserDTO;
import cz.muni.fi.pa165.dto.WeaponDTO;
import cz.muni.fi.pa165.entities.User;
import cz.muni.fi.pa165.entities.Weapon;
import cz.muni.fi.pa165.facade.UserFacade;
import cz.muni.fi.pa165.service.facadeImpl.base.CrudFacadeImpl;
import cz.muni.fi.pa165.service.services.UserService;
import cz.muni.fi.pa165.service.services.mapping.MappingService;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * User facade implementation.
 *
 * @author Marek Turis
 */
@Named
@Transactional
public class UserFacadeImpl extends CrudFacadeImpl<UserDTO, User> implements UserFacade {

	private UserService userService;
	private MappingService mappingService;

	@Inject
	public UserFacadeImpl(UserService userService, MappingService mappingService) {
		super(userService, mappingService, UserDTO.class, User.class);
		this.mappingService = mappingService;
		this.userService = userService;
	}

	@Override
	public void addWeaponToUser(WeaponDTO weapon, UserDTO user) {
		if (user != null || weapon != null){
			userService.addWeaponToUser(
					mappingService.map(weapon,Weapon.class),
					mappingService.map(user, User.class));
		}
	}

	@Override
	public boolean canBeAuthenticated(UserAuthenticationDTO user) {
		return userService.canBeAuthenticated(user.getEmail(), user.getPassword());
	}

	@Override
	public UserDTO findByEmail(String email) {
		return mappingService.map(userService.findByEmail(email), UserDTO.class);
	}

	public List<WeaponDTO> getWeaponsByUserId(int userId) {
		User user = userService.findById(userId);
		return mappingService.map(user.getWeapons(), WeaponDTO.class);
	}

}
