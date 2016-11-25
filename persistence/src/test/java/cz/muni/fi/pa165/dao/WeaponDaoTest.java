package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.ApplicationContextConfiguration;
import cz.muni.fi.pa165.entities.Weapon;
import cz.muni.fi.pa165.enums.Ammunition;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Basic tests for WeaponDao
 * @author Michael Cada
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationContextConfiguration.class)
@Transactional
public class WeaponDaoTest {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private WeaponDao wDao;

    private Weapon w1;
    private Weapon w2;

    @Before
    public void createWeapons() {
        w1 = new Weapon();
        w2 = new Weapon();

        w1.setAmmunition(Ammunition.ARROW);
        w1.setName("Legolasuv luk");
        w1.setRange(50);

        w2.setAmmunition(Ammunition.NONE);
        w2.setName("Excalibur");
        w2.setRange(2);

        em.persist(w1);
    }

    @Test
    public void createTest() {
        //check w2 isnt there already
        Assert.assertFalse(em.contains(w2));

        wDao.create(w2);
        Assert.assertTrue(em.contains(w2));

        //clear after test
        em.remove(w2);
        Assert.assertFalse(em.contains(w2));

    }

    @Test(expected=NoResultException.class)
    public void deleteTest() {
        wDao.delete(w1);
        wDao.findWeaponById(w1.getId());

        //clear after test to previous state
        em.persist(w1);
    }

    @Test
    public void findByIdTest() {
        Weapon foundWeapon = wDao.findWeaponById(w1.getId());
        Assert.assertTrue(foundWeapon.equals(w1));
    }

    @Test
    public void getWeaponByNameTest() {
        Assert.assertEquals(w1.getId(), wDao.getWeaponByName("Legolasuv luk").getId());
    }

    @Test
    public void updateWeaponTest() {
        w1.setName("Boromiruv luk");
        wDao.update(w1);
        Assert.assertEquals(w1.getId(), wDao.getWeaponByName("Boromiruv luk").getId());

        //clean after test to previous state
        w1.setName("Legolasuv luk");
    }

    @Test
    public void listAllTest() {
        List<Weapon> weapons = wDao.listAll();
        Assert.assertEquals(1, weapons.size());

        em.persist(w2);

        weapons = wDao.listAll();
        Assert.assertEquals(2, weapons.size());

        //clean after test to previous state
        em.remove(w2);
    }


}
