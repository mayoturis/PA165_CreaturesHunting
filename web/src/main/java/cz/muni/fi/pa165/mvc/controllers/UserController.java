package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.facade.UserFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

/**
 * @author Marek Turis
 */
@Controller
@RequestMapping("/user")
public class UserController {

	private UserFacade userFacade;

	@Inject
	public UserController(UserFacade userFacade) {
		this.userFacade = userFacade;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register() {
		return "user/register";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	@ResponseBody
	public String login() {
		return "toto pôjde";
	}
}
