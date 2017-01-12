package cz.muni.fi.pa165.service.services.base;

import cz.muni.fi.pa165.dao.base.CrudDao;
import cz.muni.fi.pa165.entities.base.Entity;
import cz.muni.fi.pa165.service.exceptions.HuntingPersistenceException;

import java.util.List;

/**
 * Service implementation for basic create, update, delete, retrieve operations.
 *
 * @author Marek Turis
 */
public class CrudServiceImpl<TEntity extends Entity> implements CrudService<TEntity> {

	private CrudDao<TEntity> crudDao;

	public CrudServiceImpl(CrudDao<TEntity> crudDao) {
		if (crudDao == null) {
			throw new IllegalArgumentException("Dao is null.");
		}

		this.crudDao = crudDao;
	}

	@Override
	public TEntity findById(int id) {
		try {
			return crudDao.findById(id);
		} catch(RuntimeException exception) {
			throw new HuntingPersistenceException("Exception while finding entity by id", exception);
		}
	}

	@Override
	public List<TEntity> findAll() {
		try {
			return crudDao.findAll();
		} catch (RuntimeException exception) {
			throw new HuntingPersistenceException("Exception while finding all entities", exception);
		}
	}

	@Override
	public int create(TEntity entity) {
		try {
			crudDao.create(entity);
			return entity.getId();
		} catch(RuntimeException exception) {
			throw new HuntingPersistenceException("Exception while creating entity", exception);
		}
	}

	@Override
	public void update(TEntity entity) {
		try {
			crudDao.update(entity);
		} catch(RuntimeException exception) {
			throw new HuntingPersistenceException("Exception while updating entity", exception);
		}
	}

	@Override
	public void delete(TEntity entity) {
		try {
			crudDao.delete(entity);
		} catch(RuntimeException exception) {
			throw new HuntingPersistenceException("Exception while deleting entity", exception);
		}
	}

	@Override
	public void delete(int entityId) {
		try {
			crudDao.delete(findById(entityId));
		} catch(RuntimeException exception) {
			throw new HuntingPersistenceException("Exception while deleting entity", exception);
		}
	}
}
