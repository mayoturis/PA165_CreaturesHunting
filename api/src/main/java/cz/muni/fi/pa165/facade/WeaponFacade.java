package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.UserDTO;
import cz.muni.fi.pa165.dto.WeaponDTO;

/**
 * @author Ondrej Zeman
 */
public interface WeaponFacade extends CrudFacade<WeaponDTO> {
	void AddWeaponToUser(WeaponDTO weapon, UserDTO user);
}
