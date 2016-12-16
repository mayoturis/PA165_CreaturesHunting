package cz.muni.fi.pa165.service.services;

import cz.muni.fi.pa165.dto.UserAuthenticationDTO;
import cz.muni.fi.pa165.entities.User;
import cz.muni.fi.pa165.entities.Weapon;
import cz.muni.fi.pa165.service.services.base.CrudService;

/**
 * Interface for user service.
 *
 * @author Marek Turis
 */
public interface UserService extends CrudService<User> {
	void addWeaponToUser(Weapon weapon , User user);
	boolean canBeAuthenticated(String email, String password);
	public User findByEmail(String email);
}
