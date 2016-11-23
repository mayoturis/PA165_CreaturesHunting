package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.CrudDao;
import cz.muni.fi.pa165.service.exception.PersistenceException;

import java.util.List;

/**
 * @author Marek Turis
 */
public class CrudServiceImpl<TEntity> implements CrudService<TEntity> {

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
	public void create(TEntity entity) {
		try {
			crudDao.create(entity);
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
