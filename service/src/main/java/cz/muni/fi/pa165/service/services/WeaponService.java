package cz.muni.fi.pa165.service.services;

import cz.muni.fi.pa165.entities.Weapon;
import cz.muni.fi.pa165.service.services.base.CrudService;

/**
 * Interface for weapon service.
 *
 * @author Ondrej Zeman
 */
public interface WeaponService extends CrudService<Weapon> {

	Weapon getWeaponByName(String name);
}
