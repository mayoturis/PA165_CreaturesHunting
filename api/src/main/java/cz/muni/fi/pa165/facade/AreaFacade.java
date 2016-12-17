package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.AreaDTO;
import cz.muni.fi.pa165.dto.MonsterDTO;
import cz.muni.fi.pa165.facade.base.CrudFacade;

import java.util.List;

/**
 * Facade for area.
 *
 * @author Michael Cada
 */
public interface AreaFacade extends CrudFacade<AreaDTO> {
	void addMonsterToArea(int monsterId, int areaId);
	List<MonsterDTO> getMonstersInArea(int areaId);
}
