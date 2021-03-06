package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entities.Weapon;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Implementation of WeaponDao Interface
 *
 * @author Ondrej Zeman
 */
@Repository
public class WeaponDaoImpl implements WeaponDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void create(Weapon weapon) {
		entityManager.persist(weapon);
	}

	@Override
	public void delete(Weapon weapon) {
		entityManager.remove(findById(weapon.getId()));
	}

	@Override
	public void update(Weapon weapon) {
		entityManager.merge(weapon);
	}

	@Override
	public Weapon getWeaponByName(String name) {
		return entityManager.createQuery("SELECT w FROM Weapon w WHERE name = :name", Weapon.class)
				.setParameter("name", name)
				.getSingleResult();
	}

	@Override
	public List<Weapon> findAll() {
		return entityManager.createQuery("SELECT w FROM Weapon w", Weapon.class).getResultList();
	}

	@Override
	public Weapon findById(int id) {
		return entityManager.createQuery("SELECT w FROM Weapon w WHERE id = :id", Weapon.class)
				.setParameter("id", id)
				.getSingleResult();
	}
}
