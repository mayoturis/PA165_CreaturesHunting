package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entities.Weapon;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by zeman on 30-Oct-16.
 */
@Repository
public class WeaponDaoImpl implements WeaponDao {


    @Inject
    @PersistenceContext
    private EntityManager entityManager;

    public void create(Weapon weapon) {
        entityManager.persist(weapon);
    }

    public void delete(Weapon weapon) {
        Weapon mergedWeapon = entityManager.merge(weapon);
        entityManager.remove(mergedWeapon);
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

    public List<Weapon> getWeaponsByName(String name) {
        return entityManager.createQuery("SELECT w FROM Weapon w WHERE name = :name", Weapon.class)
                .setParameter("name", name)
                .getResultList();
    }
}
