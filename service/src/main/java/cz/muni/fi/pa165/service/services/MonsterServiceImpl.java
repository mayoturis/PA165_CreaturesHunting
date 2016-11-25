package cz.muni.fi.pa165.service.services;

import cz.muni.fi.pa165.dao.MonsterDao;
import cz.muni.fi.pa165.entities.Monster;
import cz.muni.fi.pa165.service.services.base.CrudServiceImpl;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author Simona Kruppova
 */
@Named
public class MonsterServiceImpl extends CrudServiceImpl<Monster> implements MonsterService {

	@Inject
	public MonsterServiceImpl(MonsterDao monsterDao) {
		super(monsterDao);
	}
}
