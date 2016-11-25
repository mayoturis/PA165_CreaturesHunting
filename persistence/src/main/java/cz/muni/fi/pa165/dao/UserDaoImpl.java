package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entities.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Implementation of UserDao
 * 
 * @author Marek Turis
 */
@Repository
@Transactional
public class UserDaoImpl implements UserDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<User> findAll() {
		return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
	}

	@Override
	public User findById(int id) {
		return entityManager.createQuery("SELECT u FROM User u WHERE id = :id", User.class)
				.setParameter("id", id)
				.getSingleResult();
	}

	@Override
	public void create(User user) {
		entityManager.persist(user);
	}

	@Override
	public void delete(User user) {
		entityManager.remove(findById(user.getId()));
	}

	@Override
	public void update(User user) {
		entityManager.merge(user);
	}
}
