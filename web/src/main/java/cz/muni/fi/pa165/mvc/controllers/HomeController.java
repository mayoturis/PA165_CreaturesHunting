package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Marek Turis
 */
@Controller
@RequestMapping("")
public class HomeController {

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String home(Model model, HttpServletRequest request) {
		UserDTO user = (UserDTO) request.getAttribute("authenticatedUser");
		if (user == null) {
			throw new IllegalArgumentException("empty user");
		}
		model.addAttribute("user", user);
		return "home";
	}
}
