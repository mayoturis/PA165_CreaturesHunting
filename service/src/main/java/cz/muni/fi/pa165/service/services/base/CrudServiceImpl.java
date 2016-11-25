package cz.muni.fi.pa165.service.services.base;

import cz.muni.fi.pa165.dao.base.CrudDao;
import cz.muni.fi.pa165.entities.base.Entity;
import cz.muni.fi.pa165.service.exceptions.PersistenceException;

import java.util.List;

/**
 * @author Marek Turis
 */
public class CrudServiceImpl<TEntity extends Entity> implements CrudService<TEntity> {

	private CrudDao<TEntity> crudDao;

	public CrudServiceImpl(CrudDao<TEntity> crudDao) {
		this.crudDao = crudDao;
	}

	@Override
	public TEntity findById(int id) {
		try {
			return crudDao.findById(id);
		} catch(RuntimeException exception) {
			throw new PersistenceException("Exception while finding entity by id", exception);
		}
	}

	@Override
	public List<TEntity> findAll() {
		try {
			return crudDao.findAll();
		} catch (RuntimeException exception) {
			throw new PersistenceException("Exception while finding all entities", exception);
		}
	}

	@Override
	public int create(TEntity entity) {
		try {
			crudDao.create(entity);
			return entity.getId();
		} catch(RuntimeException exception) {
			throw new PersistenceException("Exception while creating entity", exception);
		}
	}

	@Override
	public void update(TEntity entity) {
		try {
			crudDao.update(entity);
		} catch(RuntimeException exception) {
			throw new PersistenceException("Exception while updating entity", exception);
		}
	}

	@Override
	public void delete(TEntity entity) {
		try {
			crudDao.delete(entity);
		} catch(RuntimeException exception) {
			throw new PersistenceException("Exception while deleting entity", exception);
		}
	}
}
