package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entities.Monster;

import java.util.List;

/**
 * Interface providing access to operations related to entities.
 *
 * @author Simona Kruppova
 */
public interface MonsterDao {

	/**
	 * Creates monster.
	 *
	 * @param monster monster object to create
	 */
	void create(Monster monster);

	/**
	 * Deletes monster.
	 *
	 * @param monster monster object to delete
	 */
	void delete(Monster monster);

	/**
	 * Finds monster by id.
	 *
	 * @param id id of a monster
	 * @return found monster
	 */
	Monster findById(Long id);

	/**
	 * Finds monster by its type.
	 * It always returns only one monster, as each monster has a unique type.
	 *
	 * @param type type of a monster
	 * @return found monster
	 */
	Monster findByType(String type);

	/**
	 * Finds all monsters.
	 *
	 * @return list of all monsters
	 */
	List<Monster> findAll();
}
