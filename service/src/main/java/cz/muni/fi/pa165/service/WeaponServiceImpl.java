package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.UserDao;
import cz.muni.fi.pa165.dao.WeaponDao;
import cz.muni.fi.pa165.entities.User;
import cz.muni.fi.pa165.entities.Weapon;
import cz.muni.fi.pa165.service.exception.PersistenceException;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author Ondrej Zeman
 */
@Named
public class WeaponServiceImpl extends CrudServiceImpl<Weapon> implements WeaponService {


	private UserDao userDao;

	private WeaponDao weaponDao;

	@Inject
	public WeaponServiceImpl(WeaponDao weaponDao , UserDao userDao)
	{
		super(weaponDao);
		this.userDao = userDao;
		this.weaponDao = weaponDao;
	}

	@Override
	public void addWeaponToUser(Weapon weapon, User user) {
		try{
			User user1 = userDao.findById(user.getId());
			Weapon weapon1 = weaponDao.findById(weapon.getId());
			user1.addWeapon(weapon1);
		}catch (RuntimeException e){
			throw new PersistenceException("Exception while finding all entities",e);
		}


	}
}
