package cz.muni.fi.pa165.facade.base;

import java.util.List;

/**
 * Facade interface for basic create, update, delete, retrieve operations.
 *
 * @author Marek Turis
 */
public interface CrudFacade<TEntityDTO> {

	/**
	 * Finds entity by id.
	 *
	 * @param id ID of entity.
	 * @return Found Entity.
	 */
	TEntityDTO findById(int id);

	/**
	 * Finds all entities.
	 *
	 * @return List of all entities.
	 */
	List<TEntityDTO> findAll();

	/**
	 * Saves new entity.
	 *
	 * @param entityDto Entity to create.
	 * @return Id of created entity
	 */
	int create(TEntityDTO entityDto);

	/**
	 * Updates entity.
	 *
	 * @param entityDto Entity to update.
	 */
	void update(TEntityDTO entityDto);

	/**
	 * Deletes entity.
	 *
	 * @param entityDto Entity to delete.
	 */
	void delete(TEntityDTO entityDto);

	/**
	 * Deletes entity with given id.
	 *
	 * @param id id of entity
	 */
	void delete(int id);
}
