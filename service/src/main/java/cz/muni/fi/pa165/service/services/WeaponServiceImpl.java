package cz.muni.fi.pa165.service.services;

import cz.muni.fi.pa165.dao.WeaponDao;
import cz.muni.fi.pa165.entities.Weapon;
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

	@Inject
	public WeaponServiceImpl(WeaponDao weaponDao) {
		super(weaponDao);
	}
}
