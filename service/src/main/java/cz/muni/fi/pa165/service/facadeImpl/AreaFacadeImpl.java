package cz.muni.fi.pa165.service.facadeImpl;

import cz.muni.fi.pa165.dto.AreaDTO;
import cz.muni.fi.pa165.dto.MonsterDTO;
import cz.muni.fi.pa165.entities.Area;
import cz.muni.fi.pa165.facade.AreaFacade;
import cz.muni.fi.pa165.service.facadeImpl.base.CrudFacadeImpl;
import cz.muni.fi.pa165.service.services.AreaService;
import cz.muni.fi.pa165.service.services.base.CrudService;
import cz.muni.fi.pa165.service.services.mapping.MappingService;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Area facade implementation.
 *
 * @author Michael Cada
 */
@Named
@Transactional
public class AreaFacadeImpl extends CrudFacadeImpl<AreaDTO, Area> implements AreaFacade {

	private AreaService areaService;
	private MappingService mappingService;

	@Inject
	public AreaFacadeImpl(AreaService crudService, MappingService mappingService) {
		super(crudService, mappingService, AreaDTO.class, Area.class);
		this.mappingService = mappingService;
		areaService = crudService;
	}

	@Override
	public void addMonsterToArea(int monsterId, int areaId) {
		areaService.addMonsterToArea(monsterId, areaId);
	}

	@Override
	public List<MonsterDTO> getMonstersInArea(int areaId) {
		return mappingService.map(areaService.getMonstersInArea(areaId), MonsterDTO.class);
	}

	@Override
	public boolean monsterExistsInArea(int monsterId, int areaId) {
		return areaService.monsterExistsInArea(monsterId, areaId);
	}

	@Override
	public void removeMonsterFromArea(int monsterId, int areaId) {
		areaService.removeMonsterFromArea(monsterId, areaId);
	}
}
