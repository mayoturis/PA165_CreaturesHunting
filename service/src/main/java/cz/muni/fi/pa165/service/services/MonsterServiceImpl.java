package cz.muni.fi.pa165.service.services;

import cz.muni.fi.pa165.dao.MonsterDao;
import cz.muni.fi.pa165.dao.WeaponDao;
import cz.muni.fi.pa165.entities.Monster;
import cz.muni.fi.pa165.entities.Weapon;
import cz.muni.fi.pa165.service.services.base.CrudServiceImpl;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Set;

/**
 * Implementation for monster service.
 *
 * @author Simona Kruppova
 */
@Named
public class MonsterServiceImpl extends CrudServiceImpl<Monster> implements MonsterService {

	private MonsterDao monsterDao;
	private WeaponDao weaponDao;

	@Inject
	public MonsterServiceImpl(MonsterDao monsterDao, WeaponDao weaponDao) {
		super(monsterDao);
		this.monsterDao = monsterDao;
		this.weaponDao = weaponDao;
	}

	@Override
	public void addWeaponToMonster(int weaponId, int monsterId) {
		Weapon weapon = weaponDao.findById(weaponId);
		Monster monster = monsterDao.findById(monsterId);

		if (monster == null || weapon == null) {
			throw new IllegalArgumentException("Monster or weapon with given id doesn't exist");
		}

		monster.addWeapon(weapon);
		weapon.addMonster(monster);
	}

	@Override
	public Set<Weapon> findWeaponsForMonster(int monsterId) {
		return findById(monsterId).getWeapons();
	}

	@Override
	public boolean monsterHasWeapon(int monsterId, int weaponId) {
		Weapon weapon = weaponDao.findById(weaponId);
		Monster monster = monsterDao.findById(monsterId);

		if (monster == null || weapon == null) {
			throw new IllegalArgumentException("Monster or weapon with given id doesn't exist");
		}

		return monster.getWeapons().contains(weapon);
	}

	@Override
	public void removeWeaponFromMonster(int weaponId, int monsterId) {
		Weapon weapon = weaponDao.findById(weaponId);
		Monster monster = monsterDao.findById(monsterId);

		if (monster == null || weapon == null) {
			throw new IllegalArgumentException("Monster or weapon with given id doesn't exist");
		}

		monster.removeWeapon(weapon);
	}
}
