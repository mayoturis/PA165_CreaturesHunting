package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.MonsterDao;
import cz.muni.fi.pa165.entities.Monster;

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
