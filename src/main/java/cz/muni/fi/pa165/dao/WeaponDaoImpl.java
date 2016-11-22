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
@Transactional
public class WeaponDaoImpl implements WeaponDao {


	@PersistenceContext
	private EntityManager entityManager;

	public void create(Weapon weapon) {
		entityManager.persist(weapon);
	}


	public void delete(Weapon weapon) {
		entityManager.remove(findWeaponById(weapon.getId()));
	}

	public void update(Weapon weapon) {
		entityManager.merge(weapon);
	}

	public Weapon findWeaponById(int id) {
		return entityManager.createQuery("SELECT w FROM Weapon w WHERE id = :id", Weapon.class)
				.setParameter("id", id)
				.getSingleResult();
	}

	public List<Weapon> listAll() {
		return entityManager.createQuery("SELECT w FROM Weapon w", Weapon.class).getResultList();

	}

	public Weapon getWeaponByName(String name) {
		return entityManager.createQuery("SELECT w FROM Weapon w WHERE name = :name", Weapon.class)
				.setParameter("name", name)
				.getSingleResult();
	}
}
