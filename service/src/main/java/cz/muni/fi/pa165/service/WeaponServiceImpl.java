package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.WeaponDao;
import cz.muni.fi.pa165.entities.Weapon;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author Ondrej Zeman
 */
@Named
public class WeaponServiceImpl extends CrudServiceImpl<Weapon> implements WeaponService {

	@Inject
	public WeaponServiceImpl(WeaponDao crudDao) {
		super(crudDao);
	}
}
