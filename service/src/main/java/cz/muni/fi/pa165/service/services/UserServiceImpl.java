package cz.muni.fi.pa165.service.services;

import cz.muni.fi.pa165.dao.UserDao;
import cz.muni.fi.pa165.dao.WeaponDao;
import cz.muni.fi.pa165.entities.User;
import cz.muni.fi.pa165.entities.Weapon;
import cz.muni.fi.pa165.service.exceptions.HuntingPersistenceException;
import cz.muni.fi.pa165.service.services.base.CrudServiceImpl;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Set;

/**
 * Implementation for user service.
 *
 * @author Marek Turis
 */
@Named
public class UserServiceImpl extends CrudServiceImpl<User> implements UserService {

	private UserDao userDao;

	private WeaponDao weaponDao;

	@Inject
	public UserServiceImpl(WeaponDao weaponDao, UserDao userDao) {
		super(userDao);
		this.userDao = userDao;
		this.weaponDao = weaponDao;
	}

	@Override
	public void addWeaponToUser(int weaponId, int userId) {
		try {
			User user = userDao.findById(userId);
			Weapon weapon = weaponDao.findById(weaponId);

			if (user == null || weapon == null) {
				throw new IllegalArgumentException("Monster or area with given id doesn't exist");
			}

			user.addWeapon(weapon);
			weapon.addUser(user);
		} catch (RuntimeException e) {
			throw new HuntingPersistenceException("Error while adding weapon to user", e);
		}
	}

	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}

	@Override
	public boolean userHasWeapon(int weaponId, int userId) {
		User user = userDao.findById(userId);
		Weapon weapon = weaponDao.findById(weaponId);

		if (user == null || weapon == null) {
			throw new IllegalArgumentException("User or weapon with given id doesn't exist");
		}

		return user.getWeapons().contains(weapon);
	}

	@Override
	public Set<Weapon> getWeaponsByUserId(int userId) {
		return findById(userId).getWeapons();
	}

	@Override
	public void removeWeaponFromUser(int weaponId, int userId) {
		User user = userDao.findById(userId);
		Weapon weapon = weaponDao.findById(weaponId);

		if (user == null || weapon == null) {
			throw new IllegalArgumentException("User or weapon with given id doesn't exist");
		}

		user.removeWeapon(weapon);
	}

	@Override
	public boolean canBeAuthenticated(String email, String password) {
		User user = findByEmail(email);
		if (user == null) {
			return false;
		}

		return validatePassword(password, user.getPassword());
	}

	@Override
	public int create(User user) {
		String hashedPassword = createHash(user.getPassword());
		user.setPassword(hashedPassword);
		return super.create(user);
	}

	private static String createHash(String password) {
		final int SALT_BYTE_SIZE = 24;
		final int HASH_BYTE_SIZE = 24;
		final int PBKDF2_ITERATIONS = 1000;
		// Generate a random salt
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[SALT_BYTE_SIZE];
		random.nextBytes(salt);
		// Hash the password
		byte[] hash = pbkdf2(password.toCharArray(), salt, PBKDF2_ITERATIONS, HASH_BYTE_SIZE);
		// format iterations:salt:hash
		return PBKDF2_ITERATIONS + ":" + toHex(salt) + ":" + toHex(hash);
	}

	private static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int bytes) {
		try {
			PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
			return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).getEncoded();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static boolean validatePassword(String password, String correctHash) {
		if(password==null) return false;
		if(correctHash==null) throw new IllegalArgumentException("password hash is null");
		String[] params = correctHash.split(":");
		int iterations = Integer.parseInt(params[0]);
		byte[] salt = fromHex(params[1]);
		byte[] hash = fromHex(params[2]);
		byte[] testHash = pbkdf2(password.toCharArray(), salt, iterations, hash.length);
		return slowEquals(hash, testHash);
	}

	/**
	 * Compares two byte arrays in length-constant time. This comparison method
	 * is used so that password hashes cannot be extracted from an on-line
	 * system using a timing attack and then attacked off-line.
	 *
	 * @param a the first byte array
	 * @param b the second byte array
	 * @return true if both byte arrays are the same, false if not
	 */
	private static boolean slowEquals(byte[] a, byte[] b) {
		int diff = a.length ^ b.length;
		for (int i = 0; i < a.length && i < b.length; i++)
			diff |= a[i] ^ b[i];
		return diff == 0;
	}

	private static byte[] fromHex(String hex) {
		byte[] binary = new byte[hex.length() / 2];
		for (int i = 0; i < binary.length; i++) {
			binary[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}
		return binary;
	}

	private static String toHex(byte[] array) {
		BigInteger bi = new BigInteger(1, array);
		String hex = bi.toString(16);
		int paddingLength = (array.length * 2) - hex.length();
		return paddingLength > 0 ? String.format("%0" + paddingLength + "d", 0) + hex : hex;
	}
}
