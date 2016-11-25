package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entities.User;
import cz.muni.fi.pa165.entities.Weapon;

/**
 * @author Ondrej Zeman
 */
public interface WeaponService extends CrudService<Weapon> {

	void AddWeaponToUser(Weapon weapon , User user);

}
