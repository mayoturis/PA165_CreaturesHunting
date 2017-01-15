package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.WeaponDTO;
import cz.muni.fi.pa165.facade.base.CrudFacade;

import java.util.List;

/**
 * Facade for weapon.
 *
 * @author Ondrej Zeman
 */
public interface WeaponFacade extends CrudFacade<WeaponDTO> {
	WeaponDTO getWeaponByName(String name);
}
