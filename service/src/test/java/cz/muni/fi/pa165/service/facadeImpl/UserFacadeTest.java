package cz.muni.fi.pa165.service.facadeImpl;

import cz.muni.fi.pa165.dto.UserDTO;
import cz.muni.fi.pa165.entities.User;
import cz.muni.fi.pa165.facade.UserFacade;
import cz.muni.fi.pa165.service.services.UserService;
import cz.muni.fi.pa165.service.services.mapping.MappingService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.verification.VerificationMode;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Tests for UserFacade
 * @author Ondrej Zeman
 */
@Transactional
@RunWith(MockitoJUnitRunner.class)
public class UserFacadeTest {


	private static VerificationMode oneTime = times(1);

	@Mock
	private MappingService mappingService;

	@Mock
	private UserService userService;

	@Mock
	private User user;
	@Mock
	private UserDTO userDTO;

	private UserFacade userFacade;
	@Before
	public void setup(){
		userFacade=new UserFacadeImpl(userService,mappingService);
	}

	@Test
	public void create(){
		Mockito.when(mappingService.map(userDTO,User.class)).thenReturn(user);
		when(userService.create(user)).thenReturn(1337);

		int id = userFacade.create(userDTO);

		verify(userService, oneTime).create(user);
		Assert.assertEquals(1337, id);
	}
	@Test
	public void update() {
		when(mappingService.map(userDTO, User.class)).thenReturn(user);

		userFacade.update(userDTO);

		verify(userService, oneTime).update(user);
	}

	@Test
	public void delete() {
		when(mappingService.map(userDTO, User.class)).thenReturn(user);

		userFacade.delete(userDTO);

		verify(userService, oneTime).delete(user);
	}

	@Test
	public void deleteById() {
		int id = 1337;
		when(userService.findById(id)).thenReturn(user);

		userFacade.delete(id);

		verify(userService, oneTime).delete(user);
	}


	@Test
	public void findById() {
		int id = 1337;
		when(userService.findById(id)).thenReturn(user);
		when(mappingService.map(user, UserDTO.class)).thenReturn(userDTO);

		UserDTO actual = userFacade.findById(id);

		verify(userService, oneTime).findById(id);
		Assert.assertEquals(userDTO, actual);
	}

	@Test
	public void findAll() {
		List<User> userList = Collections.singletonList(user);
		List<UserDTO> userDTOList = Collections.singletonList(userDTO);
		when(userService.findAll()).thenReturn(userList);
		when(mappingService.map(userList, UserDTO.class)).thenReturn(userDTOList);

		List<UserDTO> list = userFacade.findAll();

		Assert.assertEquals(userDTOList, list);
	}
	


}
