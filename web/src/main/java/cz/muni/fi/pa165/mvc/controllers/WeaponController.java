package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.dto.WeaponDTO;
import cz.muni.fi.pa165.facade.WeaponFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;

/**
 * @author Ondrej Zeman
 */
@Controller
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

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newWeapon(Model model) {
		model.addAttribute("WeaponCreate", new WeaponDTO() {
		});
		return "weapon/new";
	}

}
