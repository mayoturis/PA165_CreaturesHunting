package cz.muni.fi.pa165.service.services;

import cz.muni.fi.pa165.entities.User;
import cz.muni.fi.pa165.entities.Weapon;
import cz.muni.fi.pa165.service.services.base.CrudService;

import java.util.Set;

/**
 * Interface for user service.
 *
 * @author Marek Turis
 */
public interface UserService extends CrudService<User> {
	void addWeaponToUser(int weaponId, int userId);
	boolean canBeAuthenticated(String email, String password);
	public User findByEmail(String email);
	boolean userHasWeapon(int weaponId, int userId);
	Set<Weapon> getWeaponsByUserId(int userId);
	void removeWeaponFromUser(int weaponId, int userId);
}
