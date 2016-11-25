package cz.muni.fi.pa165.service.facadeImpl;

import cz.muni.fi.pa165.dto.UserDTO;
import cz.muni.fi.pa165.entities.User;
import cz.muni.fi.pa165.facade.UserFacade;
import cz.muni.fi.pa165.service.facadeImpl.base.CrudFacadeImpl;
import cz.muni.fi.pa165.service.services.base.CrudService;
import cz.muni.fi.pa165.service.services.mapping.MappingService;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * User facade implementation.
 *
 * @author Marek Turis
 */
@Named
@Transactional
public class UserFacadeImpl extends CrudFacadeImpl<UserDTO, User> implements UserFacade {

	@Inject
	public UserFacadeImpl(CrudService<User> crudService, MappingService mappingService) {
		super(crudService, mappingService, UserDTO.class, User.class);
	}
}
