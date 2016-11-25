package cz.muni.fi.pa165.dao;


import cz.muni.fi.pa165.entities.Area;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Implementation of AreaDao interface.
 *
 * @author Michael Cada
 */
@Repository
@Transactional
public class AreaDaoImpl implements AreaDao {

    @PersistenceContext
    private EntityManager em;


    public List<Area> findAll() {
        return em.createQuery("Select a from Area a", Area.class).getResultList();
    }

    public Area findById(int id) {
        return em.find(Area.class, id);
    }

    public void create(Area newArea) {
        em.persist(newArea);
    }

    public void delete(Area toBeDeletedArea) {
        em.remove(findById(toBeDeletedArea.getId()));
    }

    @Override
    public void update(Area toBeUpdatedArea) {
        em.merge(toBeUpdatedArea);
    }
}
