package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entities.Weapon;

import java.util.List;

/**
 * Interface for working with weapon object in database
 * @author Ondrej Zeman
 */
public interface WeaponDao {
    /**
     * Adds new weapon to database
     *
     * @param weapon to be created
     */
    void create(Weapon weapon);

    /**
     * deletes weapon from database
     *
     * @param weapon weapon to be deleted
     */
    void delete(Weapon weapon);

    /**
     * update weapon from database
     *
     * @param weapon to be updated
     */
    void update(Weapon weapon);

    /**
     * finds weapon by id
     *
     * @param id of weapon
     * @return found weapon
     */
    Weapon findWeaponById(int id);

    /**
     * lists all weapons
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

