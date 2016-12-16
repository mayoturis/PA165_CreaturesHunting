package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.dto.UserDTO;
import cz.muni.fi.pa165.facade.UserFacade;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;
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
	public String doRegister(@ModelAttribute("user") UserDTO model, UriComponentsBuilder uriBuilder) {
		userFacade.create(model);
		return "redirect:" + uriBuilder.path("/user/list").toUriString();
	}

	// todo add only admin
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		List<UserDTO> users = userFacade.findAll();
		model.addAttribute("users", users);
		return "user/list";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	@ResponseBody
	public String login() {
		return "toto pôjde";
	}
}
