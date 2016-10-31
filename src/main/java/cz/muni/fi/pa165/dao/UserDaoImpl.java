package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entities.User;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Marek on 26.10.2016.
 */
public class UserDaoImpl implements UserDao {

	@PersistenceContext
	private EntityManager entityManager;

	public List<User> findAll() {
		return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
	}

	public User findById(int id) {
		return entityManager.createQuery("SELECT u FROM User u WHERE id = :id", User.class)
				.setParameter("id", id)
				.getSingleResult();
	}

	@Transactional
	public void create(User user) {
		entityManager.persist(user);
	}

	@Transactional
	public void delete(User user) {
		entityManager.remove(findById(user.getId()));
	}
}
