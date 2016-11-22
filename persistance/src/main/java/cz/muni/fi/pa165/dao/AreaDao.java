package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entities.Area;

import java.util.List;

/**
 * Interface for manipulation with Area database.
 * @author Michael Cada
 */
public interface AreaDao {
    /**
     * Finds all areas
     * @return list of all areas
     */
    List<Area> findAll();

    /**
     * Finds area by id
     * @param id id of an area which you are looking for
     * @return found area
     */
    Area findById(int id);

    /**
     * persists new Area
     * @param newArea Area to be added
     */
    void create(Area newArea);

    /**
     * Deletes area from database
     * @param toBeDeletedArea area to be deleted
     */
    void delete(Area toBeDeletedArea);

	/**
	 * update Area information
	 *
	 * @param toBeUpdatedArea to be updated
	 */
	void update(Area toBeUpdatedArea);
}
