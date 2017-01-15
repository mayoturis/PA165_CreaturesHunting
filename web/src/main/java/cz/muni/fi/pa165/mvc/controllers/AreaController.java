package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.dto.AreaDTO;
import cz.muni.fi.pa165.dto.UserDTO;
import cz.muni.fi.pa165.enums.DangerLevel;
import cz.muni.fi.pa165.facade.AreaFacade;
import cz.muni.fi.pa165.facade.MonsterFacade;
import cz.muni.fi.pa165.facade.UserFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * SpringMVC Controller for administering areas.
 *
 * @author Michael Cada
 */
@Controller
@RequestMapping("/area")
public class AreaController {
	@Inject
	private AreaFacade areaFacade;

	@Inject
	private MonsterFacade monsterFacade;

	@Inject
	private UserFacade userFacade;

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
		redirectAttributes.addFlashAttribute("alert_success", "Area " + formBean.getName() + " was created");
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
	public String view(@PathVariable int id, Model model, HttpServletRequest request) {
		model.addAttribute("area", areaFacade.findById(id));
		model.addAttribute("allMonsters", monsterFacade.findAll());
		model.addAttribute("monsters", areaFacade.getMonstersInArea(id));
		model.addAttribute("probabilityToSurvive", getProbabilityToSurvive(id, request));
		return "area/details";
	}

	@RequestMapping(value = "/addMonster/{id}", method = RequestMethod.POST)
	public String addMonster(@PathVariable int id, @ModelAttribute("monsterId") int monsterId, Model model, HttpServletRequest request) {
		AreaDTO areaObject = areaFacade.findById(id);
		model.addAttribute("area", areaObject);
		model.addAttribute("allMonsters", monsterFacade.findAll());
		model.addAttribute("monsters", areaFacade.getMonstersInArea(id));
		model.addAttribute("probabilityToSurvive", getProbabilityToSurvive(id, request));

		if (areaFacade.monsterExistsInArea(monsterId, id)) {
			model.addAttribute("alert_info", "This monster already exists in this area");
			model.addAttribute("monsters", areaFacade.getMonstersInArea(id));
			return "area/details";
		}

		try {
			areaFacade.addMonsterToArea(monsterId, id);
		} catch (IllegalArgumentException ex) {
			model.addAttribute("alert_danger", ex.getMessage());
			return "area/details";
		}

		model.addAttribute("monsters", areaFacade.getMonstersInArea(id));
		model.addAttribute("probabilityToSurvive", getProbabilityToSurvive(id, request));
		model.addAttribute("alert_success", "Successfully added");
		return "area/details";
	}

	@RequestMapping(value = "/removeMonsterFromArea", method = RequestMethod.POST)
	public String removeMonsterFromArea(
			@ModelAttribute("monsterId") int monsterId,
			@ModelAttribute("areaId") int areaId,
			Model model,
			HttpServletRequest request
	) {
		areaFacade.removeMonsterFromArea(monsterId, areaId);

		AreaDTO areaObject = areaFacade.findById(areaId);
		model.addAttribute("area", areaObject);
		model.addAttribute("allMonsters", monsterFacade.findAll());
		model.addAttribute("monsters", areaFacade.getMonstersInArea(areaId));
		model.addAttribute("alert_success", "Monster successfully removed from area");
		model.addAttribute("probabilityToSurvive", getProbabilityToSurvive(areaId, request));
		return "area/details";
	}


	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String updateArea(@PathVariable int id, Model model) {
		AreaDTO area = areaFacade.findById(id);
		if (area == null) {
			return "redirect:/area/list";
		}
		model.addAttribute("area", area);
		return "area/update";
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public String updateArea(@ModelAttribute("area") AreaDTO area, @PathVariable("id") int id,
							 Model model, UriComponentsBuilder uriBuilder) {

		area.setId(id);
		areaFacade.update(area);
		return "redirect:" + uriBuilder.path("/area/list").toUriString();
	}

	private int getProbabilityToSurvive(int areaId, HttpServletRequest request) {
		String email = (String) request.getSession().getAttribute("authenticatedEmail");
		UserDTO user = userFacade.findByEmail(email);
		return areaFacade.probabilityToSurviveInArea(areaId, user.getId());
	}
}
