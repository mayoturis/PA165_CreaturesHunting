package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.MonsterDTO;
import cz.muni.fi.pa165.dto.WeaponDTO;
import cz.muni.fi.pa165.facade.base.CrudFacade;

import java.util.List;

/**
 * Facade for monster.
 *
 * @author Simona Kruppova
 */
public interface MonsterFacade extends CrudFacade<MonsterDTO> {
	void addWeaponToMonster(int weaponId, int monsterId);
	List<WeaponDTO> findWeaponsForMonster(int id);
	boolean monsterHasWeapon(int monsterId, int weaponId);
	void removeWeaponFromMonster(int weaponId, int monsterId);
}
