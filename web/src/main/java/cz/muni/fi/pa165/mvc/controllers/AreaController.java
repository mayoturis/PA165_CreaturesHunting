package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.dto.AreaDTO;
import cz.muni.fi.pa165.dto.MonsterDTO;
import cz.muni.fi.pa165.enums.DangerLevel;
import cz.muni.fi.pa165.facade.AreaFacade;
import cz.muni.fi.pa165.facade.MonsterFacade;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;
import javax.validation.Valid;

/**
 * SpringMVC Controller for administering areas.
 *
 * @author Michael Cada
 */
@Controller
@RequestMapping("/area")
@Transactional
public class AreaController {
	@Inject
	private AreaFacade areaFacade;

	@Inject
	private MonsterFacade monsterFacade;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("areas", areaFacade.findAll());
		return "/area/list";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addArea(Model model) {
		model.addAttribute("newArea", new AreaDTO() {
		});
		return "area/add";
	}

	@ModelAttribute("dangerLevel")
	public DangerLevel[] dangerLevel() {
		return DangerLevel.EASY.getDeclaringClass().getEnumConstants();
	}



	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@Valid @ModelAttribute("newArea") AreaDTO formBean, BindingResult bindingResult,
						 Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
		if (bindingResult.hasErrors()) {
			return "area/add";
		}
		int id = areaFacade.create(formBean);
		redirectAttributes.addFlashAttribute("alert_success", "Area " + id + " was created");
		return "redirect:" + uriBuilder.path("/area/list").build().encode().toUriString();
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String delete(@PathVariable int id, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
		AreaDTO area = areaFacade.findById(id);
		areaFacade.delete(id);
		redirectAttributes.addFlashAttribute("alert_success", "Area \"" + area.getName() + "\" was deleted.");
		return "redirect:" + uriBuilder.path("/area/list").toUriString();
	}

	@RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
	public String view(@PathVariable int id, Model model) {
		model.addAttribute("area", areaFacade.findById(id));
		model.addAttribute("monsters", areaFacade.findById(id).getMonsters());
		return "area/details";
	}

	@RequestMapping(value = "/addMonster/{id}", method = RequestMethod.POST)
	public String addMonster(@PathVariable int id, @ModelAttribute("monsterId") int monsterId, Model model) {
		AreaDTO areaObject = areaFacade.findById(id);
		MonsterDTO monsterObject = monsterFacade.findById(monsterId);
		areaObject.addMonster(monsterObject);
		areaFacade.update(areaObject);
		monsterFacade.update(monsterObject);
		model.addAttribute("area", areaObject);

		return "area/details";
	}
}
