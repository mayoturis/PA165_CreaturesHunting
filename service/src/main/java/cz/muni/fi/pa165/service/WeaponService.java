package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dto.UserDTO;
import cz.muni.fi.pa165.dto.WeaponDTO;
import cz.muni.fi.pa165.entities.Weapon;

/**
 * @author Ondrej Zeman
 */
public interface WeaponService extends CrudService<Weapon> {

	void AddWeaponToUser(WeaponDTO weapon , UserDTO user);

}
