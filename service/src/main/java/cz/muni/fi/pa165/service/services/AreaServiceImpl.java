package cz.muni.fi.pa165.service.services;

import cz.muni.fi.pa165.dao.AreaDao;
import cz.muni.fi.pa165.dao.MonsterDao;
import cz.muni.fi.pa165.dao.UserDao;
import cz.muni.fi.pa165.entities.Area;
import cz.muni.fi.pa165.entities.Monster;
import cz.muni.fi.pa165.entities.User;
import cz.muni.fi.pa165.entities.Weapon;
import cz.muni.fi.pa165.service.services.base.CrudServiceImpl;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Implementation for area service.
 *
 * @author Michael Cada
 */
@Named
public class AreaServiceImpl extends CrudServiceImpl<Area> implements AreaService {

	private AreaDao areaDao;
	private MonsterDao monsterDao;
	private UserDao userDao;

	@Inject
	public AreaServiceImpl(AreaDao areaDao, MonsterDao monsterDao, UserDao userDao) {
		super(areaDao);
		this.areaDao = areaDao;
		this.monsterDao = monsterDao;
		this.userDao = userDao;
	}

	@Override
	public void addMonsterToArea(int monsterId, int areaId) {
		Monster monster = monsterDao.findById(monsterId);
		Area area = areaDao.findById(areaId);

		if (monster == null || area == null) {
			throw new IllegalArgumentException("Monster or area with given id doesn't exist");
		}

		area.addMonster(monster);
		monster.addArea(area);
	}

	@Override
	public List<Monster> getMonstersInArea(int areaId) {
		return findById(areaId).getMonsters();
	}

	@Override
	public boolean monsterExistsInArea(int monsterId, int areaId) {
		Monster monster = monsterDao.findById(monsterId);
		Area area = areaDao.findById(areaId);

		if (monster == null || area == null) {
			throw new IllegalArgumentException("Monster or area with given id doesn't exist");
		}

		return area.getMonsters().contains(monster);
	}

	@Override
	public void removeMonsterFromArea(int monsterId, int areaId) {
		Monster monster = monsterDao.findById(monsterId);
		Area area = areaDao.findById(areaId);

		if (monster == null || area == null) {
			throw new IllegalArgumentException("Monster or area with given id doesn't exist");
		}

		area.removeMonster(monster);
		monster.removeArea(area);
	}

	@Override
	public void delete(int areaId) {
		final Area area = findById(areaId);
		area.getMonsters().forEach(monster -> monster.removeArea(area));
		area.getMonsters().clear();

		areaDao.delete(area);
	}

	@Override
	public int probabilityToSurviveInArea(int areaId, int userId) {
		User user = userDao.findById(userId);
		Area area = areaDao.findById(areaId);

		if (user == null || area == null) {
			throw new IllegalArgumentException("Monster or area with given id doesn't exist");
		}

		double probability = 1;

		for (Monster monster : area.getMonsters()) {
			double baseProbabilityForOneMonster = countBaseProbabilityForOneMonster(monster);
			double probabilityForUserForOneMonster = countProbabilityForOneUserForOneMonster(user, monster, baseProbabilityForOneMonster);
			probability *= probabilityForUserForOneMonster;
		}

		return (int) (probability * 100);
	}

	private double countBaseProbabilityForOneMonster(Monster monster) {
		return (100 - monster.getAgility() - monster.getStrength()) * 1.0 / 100;
	}

	private double countProbabilityForOneUserForOneMonster(User user, Monster monster, double baseProbabilityForOneMonster) {
		if (userHasEffectiveWeaponAgainstMonster(user, monster)) {
			return Math.pow(baseProbabilityForOneMonster, 1.0/4);
		}

		return baseProbabilityForOneMonster;
	}

	private boolean userHasEffectiveWeaponAgainstMonster(User user, Monster monster) {
		for (Weapon userWeapon : user.getWeapons()) {
			for (Weapon effectiveWeapon : monster.getWeapons()) {
				if (userWeapon.equals(effectiveWeapon)) {
					return true;
				}
			}
		}

		return false;
	}
}
