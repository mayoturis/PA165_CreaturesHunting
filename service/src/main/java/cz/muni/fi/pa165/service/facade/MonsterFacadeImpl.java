package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.MonsterDTO;
import cz.muni.fi.pa165.entities.Monster;
import cz.muni.fi.pa165.facade.MonsterFacade;
import cz.muni.fi.pa165.service.CrudService;
import cz.muni.fi.pa165.service.MappingService;

import javax.inject.Inject;

/**
 * @author Simona Kruppova
 */
public class MonsterFacadeImpl extends CrudFacadeImpl<MonsterDTO, Monster> implements MonsterFacade {

	@Inject
	public MonsterFacadeImpl(CrudService<Monster> crudService, MappingService mappingService) {
		super(crudService, mappingService, MonsterDTO.class, Monster.class);
	}
}
