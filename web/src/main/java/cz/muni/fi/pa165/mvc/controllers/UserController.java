package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.dto.UserAuthenticationDTO;
import cz.muni.fi.pa165.dto.UserDTO;
import cz.muni.fi.pa165.dto.WeaponDTO;
import cz.muni.fi.pa165.facade.UserFacade;
import cz.muni.fi.pa165.facade.WeaponFacade;
import cz.muni.fi.pa165.service.exceptions.HuntingPersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Marek Turis
 */
@Controller
@RequestMapping("/user")
@Transactional
public class UserController {

	final static Logger log = LoggerFactory.getLogger(UserController.class);
	private UserFacade userFacade;
	private WeaponFacade weaponFacade;

	@Inject
	public UserController(UserFacade userFacade, WeaponFacade weaponFacade) {
		this.userFacade = userFacade;
		this.weaponFacade = weaponFacade;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model) {
		model.addAttribute("user", new UserDTO());
		return "user/register";
	}

	@RequestMapping(value = "/doRegister", method = RequestMethod.POST)
	public String doRegister(@ModelAttribute("user") UserDTO user,
							 UriComponentsBuilder uriBuilder,
							 Model model,
							 HttpServletRequest request) {
		try {
			userFacade.create(user);
		} catch(HuntingPersistenceException ex) {
			model.addAttribute("user", new UserDTO());
			model.addAttribute("alert_danger", "Invalid data");
			return "user/register";
		}

		request.getSession().setAttribute("authenticatedEmail", user.getEmail());
		return "redirect:" + uriBuilder.path("").toUriString();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		List<UserDTO> users = userFacade.findAll();
		model.addAttribute("users", users);
		return "user/list";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute("user", new UserAuthenticationDTO());
		return "user/login";
	}

	@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
	public String doLogin(@ModelAttribute("user") UserAuthenticationDTO user,
						  Model model,
						  HttpServletRequest request,
						  UriComponentsBuilder uriBuilder) {
		if (!userFacade.canBeAuthenticated(user)) {
			model.addAttribute("alert_danger", "Invalid login attempt");
			return "user/login";
		}

		request.getSession().setAttribute("authenticatedEmail", user.getEmail());
		return "redirect:" + uriBuilder.path("").toUriString();
	}

	@RequestMapping(value = "/logoff", method = RequestMethod.GET)
	public String logoff(HttpServletRequest request,
						 UriComponentsBuilder uriBuilder) {
		request.getSession().removeAttribute("authenticatedEmail");
		return "redirect:" + uriBuilder.path("/user/login").toUriString();
	}

	@RequestMapping(value = "/doDelete/{id}", method = RequestMethod.POST)
	public String doDelete(UriComponentsBuilder uriBuilder,
						   @PathVariable int id) {
		userFacade.delete(id);
		return "redirect:" + uriBuilder.path("/user/list").toUriString();
	}

	@RequestMapping(value = "/arsenal", method = RequestMethod.GET)
	public String arsenal(Model model, HttpServletRequest request) {
		String email = (String) request.getSession().getAttribute("authenticatedEmail");

		UserDTO user = userFacade.findByEmail(email);
		List<WeaponDTO> weapons = userFacade.getWeaponsByUserId(user.getId());
		model.addAttribute("weapons", weapons);
		model.addAttribute("allWeapons", weaponFacade.findAll());

		return "user/arsenal";
	}

	@RequestMapping(value = "/addWeapon", method = RequestMethod.POST)
	public String addWeaponToCurrentUser(@ModelAttribute("weaponId") int weaponId, Model model,
										 HttpServletRequest request) {

		UserDTO user = userFacade.findByEmail((String) request.getSession()
				.getAttribute("authenticatedEmail"));

		List<WeaponDTO> weapons = userFacade.getWeaponsByUserId(user.getId());
		model.addAttribute("weapons", weapons);
		model.addAttribute("allWeapons", weaponFacade.findAll());

		if (userFacade.userHasWeapon(weaponId, user.getId())) {
			model.addAttribute("alert_info", "You already have this weapon");
			return "user/arsenal";
		}

		userFacade.addWeaponToUser(weaponId, user.getId());

		weapons = userFacade.getWeaponsByUserId(user.getId());
		model.addAttribute("weapons", weapons);
		model.addAttribute("alert_success", "Successfully added");
		return "user/arsenal";
	}

	@RequestMapping(value = "/removeFromArsenal/{id}", method = RequestMethod.POST)
	public String removeFromArsenal(@PathVariable int id,
						   HttpServletRequest request,
						   Model model) {
		UserDTO user = userFacade.findByEmail((String) request.getSession()
				.getAttribute("authenticatedEmail"));

		userFacade.removeWeaponFromUser(id, user.getId());

		List<WeaponDTO> weapons = userFacade.getWeaponsByUserId(user.getId());
		model.addAttribute("weapons", weapons);
		model.addAttribute("allWeapons", weaponFacade.findAll());
		model.addAttribute("alert_success", "Weapon was successfully removed from your arsenal");
		return "user/arsenal";
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String updateUser(@PathVariable int id, Model model) {
		UserDTO user = userFacade.findById(id);
		if (user == null) {
			return "redirect:/user/list";
		}
		model.addAttribute("user", user);
		return "user/update";
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public String updateUser(@ModelAttribute("user") UserDTO user, @PathVariable("id") int id,
							 Model model, UriComponentsBuilder uriBuilder) {

		user.setId(id);
		userFacade.update(user);
		return "redirect:" + uriBuilder.path("/user/list").toUriString();
	}
}
