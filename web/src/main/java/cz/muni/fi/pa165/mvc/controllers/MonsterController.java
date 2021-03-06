package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.dto.MonsterDTO;
import cz.muni.fi.pa165.facade.MonsterFacade;
import cz.muni.fi.pa165.facade.WeaponFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
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
 * SpringMVC Controller for administering monsters.
 *
 * @author Simona Kruppova
 */
@Controller
@RequestMapping("/monster")
public class MonsterController {

	private static final Logger log = LoggerFactory.getLogger(MonsterController.class);
	@Inject
	private MonsterFacade monsterFacade;
	@Inject
	private WeaponFacade weaponFacade;

	/**
	 * Shows a list of monsters with the ability to add, delete or edit.
	 *
	 * @param model data to display
	 * @return JSP page nameWeaponController
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("monsters", monsterFacade.findAll());
		return "/monster/list";
	}

	/**
	 * Prepares an empty form.
	 *
	 * @param model data to be displayed
	 * @return JSP page
	 */
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newMonster(Model model) {
		model.addAttribute("monsterCreate", new MonsterDTO());
		return "monster/new";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@Valid @ModelAttribute("monsterCreate") MonsterDTO formBean, BindingResult bindingResult,
						 Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
		log.debug("create(monsterCreate={})", formBean);
		if (bindingResult.hasErrors()) {
			for (ObjectError ge : bindingResult.getGlobalErrors()) {
				log.trace("ObjectError: {}", ge);
			}
			for (FieldError fe : bindingResult.getFieldErrors()) {
				model.addAttribute(fe.getField() + "_error", true);
				log.trace("FieldError: {}", fe);
			}
			return "monster/new";
		}
		int id = monsterFacade.create(formBean);
		redirectAttributes.addFlashAttribute("alert_success",  formBean.getType() + " monster type was created");
		return "redirect:" + uriBuilder.path("/monster/view/{id}").buildAndExpand(id).encode().toUriString();
	}

	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable int id, Model model) {
		log.debug("view({})", id);
		model.addAttribute("monster", monsterFacade.findById(id));
		model.addAttribute("allWeapons", weaponFacade.findAll());
		model.addAttribute("weapons", monsterFacade.findWeaponsForMonster(id));
		return "monster/view";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String delete(@PathVariable int id, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
		MonsterDTO monster = monsterFacade.findById(id);
		monsterFacade.delete(id);
		log.debug("delete({})", id);
		redirectAttributes.addFlashAttribute("alert_success", monster.getType() + " monster type was deleted.");
		return "redirect:" + uriBuilder.path("/monster/list").toUriString();
	}

	@RequestMapping(value = "/addWeapon", method = RequestMethod.POST)
	public String addWeapon(@ModelAttribute("weaponId") int weaponId,
							 @ModelAttribute("monsterId") int monsterId,
							 Model model,
							 HttpServletRequest request) {
		model.addAttribute("monster", monsterFacade.findById(monsterId));
		model.addAttribute("allWeapons", weaponFacade.findAll());
		model.addAttribute("weapons", monsterFacade.findWeaponsForMonster(monsterId));

		if (monsterFacade.monsterHasWeapon(monsterId, weaponId)) {
			model.addAttribute("alert_info", "Given weapon is already assigned to monster");
			return "monster/view";
		}

		monsterFacade.addWeaponToMonster(weaponId, monsterId);

		model.addAttribute("monster", monsterFacade.findById(monsterId));
		model.addAttribute("allWeapons", weaponFacade.findAll());
		model.addAttribute("weapons", monsterFacade.findWeaponsForMonster(monsterId));
		model.addAttribute("alert_success", "Successfully added");
		return "monster/view";
	}

	@RequestMapping(value = "/removeWeaponFromMonster", method = RequestMethod.POST)
	public String removeWeaponFromMonster(
			@ModelAttribute("monsterId") int monsterId,
			@ModelAttribute("weaponId") int weaponId,
			Model model
	) {
		monsterFacade.removeWeaponFromMonster(weaponId, monsterId);
		model.addAttribute("monster", monsterFacade.findById(monsterId));
		model.addAttribute("allWeapons", weaponFacade.findAll());
		model.addAttribute("weapons", monsterFacade.findWeaponsForMonster(monsterId));
		model.addAttribute("alert_success", "Weapon removed");
		return "monster/view";
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String updateMonster(@PathVariable int id, Model model) {
		MonsterDTO monster = monsterFacade.findById(id);
		if (monster == null) {
			return "redirect:/monster/list";
		}
		model.addAttribute("monster", monster);
		return "monster/update";
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public String updateMonster(@ModelAttribute("monster") MonsterDTO monster, @PathVariable("id") int id,
								Model model, UriComponentsBuilder uriBuilder) {

		monster.setId(id);
		monsterFacade.update(monster);
		return "redirect:" + uriBuilder.path("/monster/list").toUriString();
	}
}
