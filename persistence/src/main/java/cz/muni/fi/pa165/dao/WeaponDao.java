package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.dao.base.CrudDao;
import cz.muni.fi.pa165.entities.Weapon;

import java.util.List;

/**
 * Interface for working with weapon object in database
 *
 * @author Ondrej Zeman
 */
public interface WeaponDao extends CrudDao<Weapon> {

	/**
	 * finds weapon by id
	 *
	 * @param id of weapon
	 * @return found weapon
	 */
	Weapon findWeaponById(int id);

	/**
	 * lists all weapons
	 *
	 * @return all weapons
	 */
	List<Weapon> listAll();

	/**
	 * finds weapon by name
	 *
	 * @param name of weapon
	 * @return found weapon
	 */
	Weapon getWeaponByName(String name);
}

