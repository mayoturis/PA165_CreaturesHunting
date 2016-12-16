package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.dto.UserAuthenticationDTO;
import cz.muni.fi.pa165.dto.UserDTO;
import cz.muni.fi.pa165.facade.UserFacade;
import cz.muni.fi.pa165.service.exceptions.HuntingPersistenceException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

	private UserFacade userFacade;

	@Inject
	public UserController(UserFacade userFacade) {
		this.userFacade = userFacade;
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

	// todo add only admin
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
}
