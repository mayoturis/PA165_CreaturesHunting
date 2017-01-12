package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.dto.WeaponDTO;
import cz.muni.fi.pa165.enums.Ammunition;
import cz.muni.fi.pa165.facade.WeaponFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
import javax.validation.Valid;

/**
 * @author Ondrej Zeman
 */
@Controller
@Transactional
@RequestMapping("/weapon")
public class WeaponController {

	private static final Logger log = LoggerFactory.getLogger(WeaponController.class);

	private WeaponFacade weaponFacade;

	@Inject
	public WeaponController(WeaponFacade weaponFacade) {
		this.weaponFacade = weaponFacade;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("weapons", weaponFacade.findAll());
		return "/weapon/list";
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createNewWeapon(Model model) {
		model.addAttribute("WeaponCreate", new WeaponDTO() {
		});
		return "weapon/create";
	}


	@ModelAttribute("ammunitions")
	public Ammunition[] ammunitions() {
		return Ammunition.ARROW.getDeclaringClass().getEnumConstants();

	}

	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable int id, Model model) {
		log.debug("view({})", id);
		model.addAttribute("weapon", weaponFacade.findById(id));
		return "weapon/view";
	}


	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@Valid @ModelAttribute("WeaponCreate") WeaponDTO formBean, BindingResult bindingResult,
						 Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
		log.debug("create(WeaponCreate={})", formBean);
		if (bindingResult.hasErrors()) {
			for (ObjectError ge : bindingResult.getGlobalErrors()) {
				log.trace("ObjectError: {}", ge);
			}
			for (FieldError fe : bindingResult.getFieldErrors()) {
				model.addAttribute(fe.getField() + "_error", true);
				log.trace("FieldError: {}", fe);
			}
			return "weapon/create";
		}
		int id = weaponFacade.create(formBean);

		redirectAttributes.addFlashAttribute("alert_success", "Weapon " + id + " was created");
		return "redirect:" + uriBuilder.path("/weapon/list").toUriString();
	}


	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String delete(@PathVariable int id, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
		WeaponDTO weapon = weaponFacade.findById(id);
		weaponFacade.delete(id);
		log.debug("delete({})", id);
		redirectAttributes.addFlashAttribute("alert_success", "Weapon \"" + weapon.getName() + "\" was deleted.");
		return "redirect:" + uriBuilder.path("/weapon/list").toUriString();
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String updateWeapon(@PathVariable int id, Model model) {
		WeaponDTO weapon = weaponFacade.findById(id);
		if (weapon == null) {
			return "redirect:/weapon/list";
		}
		model.addAttribute("weapon", weapon);
		return "weapon/update";
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public String updateWeapon(@ModelAttribute("weapon") WeaponDTO weapon, @PathVariable("id") int id,
							   Model model, UriComponentsBuilder uriBuilder) {

		weapon.setId(id);
		weaponFacade.update(weapon);
		log.debug(weapon.toString() + " updated");
		return "redirect:" + uriBuilder.path("/weapon/list").toUriString();
	}

}
