package cz.muni.fi.pa165.service.services;

import cz.muni.fi.pa165.entities.Monster;
import cz.muni.fi.pa165.entities.Weapon;
import cz.muni.fi.pa165.service.services.base.CrudService;

import java.util.Set;

/**
 * Interface for monster service.
 *
 * @author Simona Kruppova
 */
public interface MonsterService extends CrudService<Monster> {
	void addWeaponToMonster(int weaponId, int monsterId);
	Set<Weapon> findWeaponsForMonster(int monsterId);
	boolean monsterHasWeapon(int monsterId, int weaponId);
	void removeWeaponFromMonster(int weaponId, int monsterId);
}
