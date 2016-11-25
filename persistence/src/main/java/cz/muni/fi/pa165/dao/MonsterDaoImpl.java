package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entities.Monster;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Implementation of monster dao interface.
 *
 * @author Simona Kruppova
 */
@Repository
public class MonsterDaoImpl implements MonsterDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void create(Monster monster) {
		em.persist(monster);
	}

	@Override
	public void update(Monster monster) {
		em.merge(monster);
	}

	@Override
	public void delete(Monster monster) {
		em.remove(findById(monster.getId()));
	}

	@Override
	public Monster findById(int id) {
		return em.find(Monster.class, id);
	}

	@Override
	public Monster findByType(String type) {
		return em.createQuery("select m from Monster m where type = :type", Monster.class)
				.setParameter("type", type)
				.getSingleResult();
	}

	@Override
	public List<Monster> findAll() {
		return em.createQuery("select m from Monster m", Monster.class).getResultList();
	}
}
