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
	void addWeaponToUser(WeaponDTO weapon, UserDTO user);
	boolean canBeAuthenticated(UserAuthenticationDTO user);
	UserDTO findByEmail(String email);

	List<WeaponDTO> getWeaponsByUserId(int userId);
}
