package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entities.Weapon;

import java.util.List;

/**
 * Created by zeman on 30-Oct-16.
 */
public interface WeaponDao {
    void create(Weapon weapon);

    void delete(Weapon weapon);

    void update(Weapon weapon);

    Weapon findWeaponById(int id);

    List<Weapon> listAll();

    List<Weapon> getWeaponsByName(String name);
}
