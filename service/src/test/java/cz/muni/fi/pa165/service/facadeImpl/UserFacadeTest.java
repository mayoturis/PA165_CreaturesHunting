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

import static org.mockito.Mockito.times;

/**
 * Tests for UserFacade
 *
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
	public void setup() {
		userFacade = new UserFacadeImpl(userService, mappingService);
	}

	@Test
	public void create() {
		Mockito.when(mappingService.map(userDTO, User.class)).thenReturn(user);
		Mockito.when(userService.create(user)).thenReturn(1337);

		int id = userFacade.create(userDTO);

		Mockito.verify(userService, oneTime).create(user);
		Assert.assertEquals(1337, id);
	}

	@Test
	public void update() {
		Mockito.when(mappingService.map(userDTO, User.class)).thenReturn(user);

		userFacade.update(userDTO);

		Mockito.verify(userService, oneTime).update(user);
	}

	@Test
	public void delete() {
		Mockito.when(mappingService.map(userDTO, User.class)).thenReturn(user);

		userFacade.delete(userDTO);

		Mockito.verify(userService, oneTime).delete(user);
	}

	@Test
	public void deleteById() {
		int id = 1337;
		Mockito.when(userService.findById(id)).thenReturn(user);

		userFacade.delete(id);

		Mockito.verify(userService, oneTime).delete(user);
	}


	@Test
	public void findById() {
		int id = 1337;
		Mockito.when(userService.findById(id)).thenReturn(user);
		Mockito.when(mappingService.map(user, UserDTO.class)).thenReturn(userDTO);

		UserDTO actual = userFacade.findById(id);

		Mockito.verify(userService, oneTime).findById(id);
		Assert.assertEquals(userDTO, actual);
	}

	@Test
	public void findAll() {
		List<User> userList = Collections.singletonList(user);
		List<UserDTO> userDTOList = Collections.singletonList(userDTO);
		Mockito.when(userService.findAll()).thenReturn(userList);
		Mockito.when(mappingService.map(userList, UserDTO.class)).thenReturn(userDTOList);

		List<UserDTO> list = userFacade.findAll();

		Assert.assertEquals(userDTOList, list);
	}


}
