package cz.muni.fi.pa165.service.facadeImpl;

import cz.muni.fi.pa165.dto.AreaDTO;
import cz.muni.fi.pa165.entities.Area;
import cz.muni.fi.pa165.facade.AreaFacade;
import cz.muni.fi.pa165.service.facadeImpl.base.CrudFacadeImpl;
import cz.muni.fi.pa165.service.services.base.CrudService;
import cz.muni.fi.pa165.service.services.mapping.MappingService;

import javax.inject.Inject;

/**
 * @author Michael Cada
 */
public class AreaFacadeImpl extends CrudFacadeImpl<AreaDTO, Area> implements AreaFacade {

	@Inject
	public AreaFacadeImpl(CrudService<Area> crudService, MappingService mappingService) {
		super(crudService, mappingService, AreaDTO.class, Area.class);
	}
}
