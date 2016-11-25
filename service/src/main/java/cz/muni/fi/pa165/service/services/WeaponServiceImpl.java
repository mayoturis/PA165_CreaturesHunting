package cz.muni.fi.pa165.service.services;

import cz.muni.fi.pa165.dao.UserDao;
import cz.muni.fi.pa165.dao.WeaponDao;
import cz.muni.fi.pa165.entities.User;
import cz.muni.fi.pa165.entities.Weapon;
import cz.muni.fi.pa165.service.exceptions.PersistenceException;
import cz.muni.fi.pa165.service.services.base.CrudServiceImpl;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Implementation for weapon service.
 *
 * @author Ondrej Zeman
 */
@Named
public class WeaponServiceImpl extends CrudServiceImpl<Weapon> implements WeaponService {


	private UserDao userDao;

	private WeaponDao weaponDao;

	@Inject
	public WeaponServiceImpl(WeaponDao weaponDao, UserDao userDao) {
		super(weaponDao);
		this.userDao = userDao;
		this.weaponDao = weaponDao;
	}

	@Override
	public void addWeaponToUser(Weapon weapon, User user) {
		try {
			User user1 = userDao.findById(user.getId());
			Weapon weapon1 = weaponDao.findById(weapon.getId());
			user1.addWeapon(weapon1);
		} catch (RuntimeException e) {
			throw new PersistenceException("Exception in thrown by Hibernate", e);
		}
	}
}
