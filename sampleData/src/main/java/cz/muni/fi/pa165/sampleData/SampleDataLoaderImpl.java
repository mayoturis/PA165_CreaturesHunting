package cz.muni.fi.pa165.sampleData;

import cz.muni.fi.pa165.entities.Area;
import cz.muni.fi.pa165.entities.Monster;
import cz.muni.fi.pa165.entities.User;
import cz.muni.fi.pa165.entities.Weapon;
import cz.muni.fi.pa165.enums.Ammunition;
import cz.muni.fi.pa165.enums.DangerLevel;
import cz.muni.fi.pa165.enums.Gender;
import cz.muni.fi.pa165.service.services.AreaService;
import cz.muni.fi.pa165.service.services.MonsterService;
import cz.muni.fi.pa165.service.services.UserService;
import cz.muni.fi.pa165.service.services.WeaponService;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;

/**
 * @ author Ondrej Zeman
 */
@Named
@Transactional
public class SampleDataLoaderImpl implements SampleDataLoader {

	@Inject
	private UserService userService;

	@Inject
	private MonsterService monsterService;

	@Inject
	private WeaponService weaponService;

	@Inject
	private AreaService areaService;

	@Override
	public void loadData() throws IOException, ParseException {

		User user1 = new User("Aragorn", "aragorn@gmail.com", "password", 20, Gender.MALE);
		User user2 = new User("Boromir", "boromir@gmail.com", "password", 25, Gender.MALE);
		User user3 = new User("Legolas", "legolas@gmail.com", "password", 32, Gender.MALE);
		User admin = new User("admin", "admin@gmail.com", "password", 28, Gender.FEMALE);
		admin.setAdmin(true);
		userService.create(user1);
		userService.create(user2);
		userService.create(user3);
		userService.create(admin);

		Weapon weapon1 = new Weapon("Bow", Ammunition.ARROW);
		Weapon weapon2 = new Weapon("Sword", Ammunition.NONE);
		Weapon weapon3 = new Weapon("Dagger", Ammunition.NONE);
		Weapon weapon4 = new Weapon("Laser gun", Ammunition.NONE);
		weaponService.create(weapon4);
		weaponService.create(weapon1);
		weaponService.create(weapon2);
		weaponService.create(weapon3);

		Monster monster1 = new Monster("Orc", new BigDecimal(100), new BigDecimal(100), 6, 3);
		Monster monster2 = new Monster("Troll", new BigDecimal(250), new BigDecimal(200), 4, 7);
		Monster monster3 = new Monster("Uruk-hai", new BigDecimal(175), new BigDecimal(120), 5, 6);
		Monster monster4 = new Monster("Pixie", new BigDecimal(10), new BigDecimal(3), 9, 1);
		Monster monster5 = new Monster("Dragon", new BigDecimal(700), new BigDecimal(600), 8, 10);
		Monster monster6 = new Monster("Great Spider", new BigDecimal(240), new BigDecimal(100), 8, 8);
		monsterService.create(monster1);
		monsterService.create(monster2);
		monsterService.create(monster3);
		monsterService.create(monster4);
		monsterService.create(monster5);
		monsterService.create(monster6);

		Area area1 = new Area("Mordor", DangerLevel.EXTREME, BigDecimal.TEN);
		area1.setDescription("A region occupied and controlled by Sauron, in the southeast of northwestern Middle-earth to the East of Anduin, the great river.");
		Area area2 = new Area("Shire", DangerLevel.EASY, BigDecimal.TEN);
		area2.setDescription("Area located in the northwest of the Middle-earth, in the large region of Eriador and the Kingdom of Arnor.");
		Area area3 = new Area("Isengard", DangerLevel.HARD, BigDecimal.TEN);
		area3.setDescription("Area located at the north-western corner of Rohan, guarding the Fords of Isen from enemy incursions into Calenardhon together with the fortress of Aglarond to its south.");
		Area area4 = new Area("Unexplored area", DangerLevel.HARD, BigDecimal.valueOf(30));
		area4.setDescription("Big unexplored area with lots of dangerous creatures roaming about.");
		areaService.create(area1);
		areaService.create(area2);
		areaService.create(area3);
		areaService.create(area4);

		areaService.addMonsterToArea(area1.getId(), monster1.getId());
		areaService.addMonsterToArea(area1.getId(), monster2.getId());
		areaService.addMonsterToArea(area3.getId(), monster3.getId());
		areaService.addMonsterToArea(area4.getId(), monster1.getId());
		areaService.addMonsterToArea(area4.getId(), monster2.getId());
		areaService.addMonsterToArea(area4.getId(), monster3.getId());
		areaService.addMonsterToArea(area4.getId(), monster4.getId());
		areaService.addMonsterToArea(area4.getId(), monster5.getId());
		areaService.addMonsterToArea(area4.getId(), monster6.getId());

		monsterService.addWeaponToMonster(weapon1.getId(), monster2.getId());
		monsterService.addWeaponToMonster(weapon1.getId(), monster1.getId());
		monsterService.addWeaponToMonster(weapon2.getId(), monster1.getId());
		monsterService.addWeaponToMonster(weapon2.getId(), monster3.getId());
		monsterService.addWeaponToMonster(weapon1.getId(), monster4.getId());
		monsterService.addWeaponToMonster(weapon2.getId(), monster4.getId());
		monsterService.addWeaponToMonster(weapon3.getId(), monster4.getId());
		monsterService.addWeaponToMonster(weapon4.getId(), monster4.getId());

		userService.addWeaponToUser(weapon1.getId(), user3.getId());
		userService.addWeaponToUser(weapon2.getId(), user1.getId());
		userService.addWeaponToUser(weapon3.getId(), user2.getId());
		userService.addWeaponToUser(weapon2.getId(), user2.getId());
		userService.addWeaponToUser(weapon4.getId(), admin.getId());
	}
}
