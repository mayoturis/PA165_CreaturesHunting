package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.dao.base.CrudDao;
import cz.muni.fi.pa165.entities.Weapon;

/**
 * Interface for working with weapon object in database
 *
 * @author Ondrej Zeman
 */
public interface WeaponDao extends CrudDao<Weapon> {



	/**
	 * finds weapon by name
	 *
	 * @param name of weapon
	 * @return found weapon
	 */
	Weapon getWeaponByName(String name);
}

