package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entities.base.Entity;

import java.util.List;

/**
 * @author Marek Turis
 */
public interface CrudService<TEntity extends Entity> {

	/**
	 * Finds entity by id.
	 *
	 * @param id ID of entity.
	 * @return Found Entity.
	 */
	TEntity findById(int id);

	/**
	 * Finds all entities.
	 *
	 * @return List of all entities.
	 */
	List<TEntity> findAll();

	/**
	 * Saves new entity.
	 *
	 * @param entity Entity to create.
	 * @return id of created entity
	 */
	int create(TEntity entity);

	/**
	 * Updates entity.
	 *
	 * @param entity Entity to update.
	 */
	void update(TEntity entity);

	/**
	 * Deletes entity.
	 *
	 * @param entity Entity to delete.
	 */
	void delete(TEntity entity);

}
