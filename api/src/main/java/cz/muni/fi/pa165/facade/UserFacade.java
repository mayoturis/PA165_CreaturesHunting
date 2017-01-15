package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.UserAuthenticationDTO;
import cz.muni.fi.pa165.dto.UserDTO;
import cz.muni.fi.pa165.dto.WeaponDTO;
import cz.muni.fi.pa165.facade.base.CrudFacade;

import java.util.List;

/**
 * Facade for user.
 *
 * @author Marek Turis
 */
public interface UserFacade extends CrudFacade<UserDTO> {
	void addWeaponToUser(int weaponId, int userId);
	boolean canBeAuthenticated(UserAuthenticationDTO user);
	UserDTO findByEmail(String email);
	boolean userHasWeapon(int weaponId, int userId);
	List<WeaponDTO> getWeaponsByUserId(int userId);
	void removeWeaponFromUser(int weaponId, int userId);
}
