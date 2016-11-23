package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entities.Monster;

import java.util.List;

/**
 * Interface providing access to operations related to entities.
 *
 * @author Simona Kruppova
 */
public interface MonsterDao extends CrudDao<Monster> {

	/**
	 * Finds monster by its type.
	 * It always returns only one monster, as each monster has a unique type.
	 *
	 * @param type type of a monster
	 * @return found monster
	 */
	Monster findByType(String type);
}
