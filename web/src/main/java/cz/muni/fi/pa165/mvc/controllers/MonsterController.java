package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.facade.MonsterFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;

/**
 * SpringMVC Controller for administering monsters.
 *
 * @author Simona Kruppova
 */
@Controller
@RequestMapping("/monster")
public class MonsterController {

	@Inject
	private MonsterFacade monsterFacade;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("monsters", monsterFacade.findAll());
		return "/monster/list";
	}
}
