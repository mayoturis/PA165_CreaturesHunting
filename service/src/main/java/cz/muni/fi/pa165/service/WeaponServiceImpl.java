package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.UserDao;
import cz.muni.fi.pa165.dao.WeaponDao;
import cz.muni.fi.pa165.dto.UserDTO;
import cz.muni.fi.pa165.dto.WeaponDTO;
import cz.muni.fi.pa165.entities.User;
import cz.muni.fi.pa165.entities.Weapon;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author Ondrej Zeman
 */
@Named
public class WeaponServiceImpl extends CrudServiceImpl<Weapon> implements WeaponService {


	@Inject
	private UserDao userDao;

	@Inject
	public WeaponServiceImpl(WeaponDao weaponDao , UserDao userDao)
	{
		super(weaponDao);
		this.userDao=userDao;
	}

	@Override
	public void AddWeaponToUser(WeaponDTO weapon, UserDTO user) {
		User user1 = userDao.findById(user.getId());
		Weapon weapon1 = crudDao.findById(weapon.getId());
		user1.addWeapon(weapon1);
		userDao.update(user1);
		
	}
}
