package cz.muni.fi.pa165.service.services;

import cz.muni.fi.pa165.entities.User;
import cz.muni.fi.pa165.entities.Weapon;
import cz.muni.fi.pa165.service.services.base.CrudService;

/**
 * @author Ondrej Zeman
 */
public interface WeaponService extends CrudService<Weapon> {

	void addWeaponToUser(Weapon weapon , User user);

}
