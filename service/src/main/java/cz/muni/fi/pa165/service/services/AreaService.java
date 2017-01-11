package cz.muni.fi.pa165.service.services;

import cz.muni.fi.pa165.dto.MonsterDTO;
import cz.muni.fi.pa165.entities.Area;
import cz.muni.fi.pa165.entities.Monster;
import cz.muni.fi.pa165.service.services.base.CrudService;

import java.util.List;

/**
 * Interface for area service.
 *
 * @author Michael Cada
 */
public interface AreaService extends CrudService<Area> {
	void addMonsterToArea(int monsterId, int areaId);
	List<Monster> getMonstersInArea(int areaId);
	boolean monsterExistsInArea(int monsterId, int areaId);
	void removeMonsterFromArea(int monsterId, int areaId);
}
