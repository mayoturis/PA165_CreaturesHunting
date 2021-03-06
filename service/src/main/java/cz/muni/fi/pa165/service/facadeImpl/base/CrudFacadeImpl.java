package cz.muni.fi.pa165.service.facadeImpl.base;

import cz.muni.fi.pa165.entities.base.Entity;
import cz.muni.fi.pa165.facade.base.CrudFacade;
import cz.muni.fi.pa165.service.services.base.CrudService;
import cz.muni.fi.pa165.service.services.mapping.MappingService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Facade implementation for basic create, update, delete, retrieve operations.
 *
 * @author Marek Turis
 */
@Transactional
public class CrudFacadeImpl<TEntityDTO, TEntity extends Entity> implements CrudFacade<TEntityDTO> {

	private CrudService<TEntity> crudService;
	private MappingService mappingService;
	private Class<TEntityDTO> entityDtoType; // this is needed because java doesn't support getting
	private Class<TEntity> entityType;       // runtime type from generic type

	public CrudFacadeImpl(CrudService<TEntity> crudService,
						  MappingService mappingService,
						  Class<TEntityDTO> entityDtoType,
						  Class<TEntity> entityType) {
		this.crudService = crudService;
		this.mappingService = mappingService;
		this.entityDtoType = entityDtoType;
		this.entityType = entityType;
	}

	@Override
	public TEntityDTO findById(int id) {
		return mappingService.map(crudService.findById(id), entityDtoType);
	}

	@Override
	public List<TEntityDTO> findAll() {
		return mappingService.map(crudService.findAll(), entityDtoType);
	}

	@Override
	public int create(TEntityDTO entityDto) {
		TEntity entity = mappingService.map(entityDto, entityType);
		return crudService.create(entity);
	}

	@Override
	public void update(TEntityDTO entityDto) {
		TEntity entity = mappingService.map(entityDto, entityType);
		crudService.update(entity);
	}

	@Override
	public void delete(TEntityDTO entityDto) {
		TEntity entity = mappingService.map(entityDto, entityType);
		crudService.delete(entity);
	}

	@Override
	public void delete(int id) {
		crudService.delete(id);
	}
}
