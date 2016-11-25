package cz.muni.fi.pa165.dao.base;

import java.util.List;

/**
 * Interface for basic create, retrieve, update, delete operations.
 *
 * @author Marek Turis
 */
public interface CrudDao<TEntity> {

	/**
	 * Finds all entities
	 *
	 * @return all entities
	 */
	List<TEntity> findAll();

	/**
	 * Finds entity by id
	 *
	 * @param id Id of entity
	 * @return found entity
	 */
	TEntity findById(int id);

	/**
	 * Creates new entity
	 *
	 * @param entity to create
	 */
	void create(TEntity entity);

	/**
	 * Deletes entity
	 *
	 * @param entity
	 */
	void delete(TEntity entity);

	/**
	 * Updates entity
	 *
	 * @param entity
	 */
	void update(TEntity entity);
}
