package cz.muni.fi.pa165.sampleData;

import cz.muni.fi.pa165.entities.Area;
import cz.muni.fi.pa165.entities.Monster;
import cz.muni.fi.pa165.entities.User;
import cz.muni.fi.pa165.entities.Weapon;
import cz.muni.fi.pa165.enums.Ammunition;
import cz.muni.fi.pa165.enums.DangerLevel;
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
 * Created by zeman on 12-Jan-17.
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


		User user1 = new User("Aragorn", "aragorn@gmail.com", "password");
		User user2 = new User("Boromir", "boromir@gmail.com", "password");
		User user3 = new User("Legolas", "legolas@gmail.com", "password");
		User admin = new User("admin", "admin@gmail.com", "password");
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
		monsterService.create(monster1);
		monsterService.create(monster2);
		monsterService.create(monster3);

		Area area1 = new Area("Mordor", DangerLevel.EXTREME, BigDecimal.TEN);
		Area area2 = new Area("Shire", DangerLevel.EASY, BigDecimal.TEN);
		Area area3 = new Area("Isengard", DangerLevel.HARD, BigDecimal.TEN);
		areaService.create(area1);
		areaService.create(area2);
		areaService.create(area3);

		areaService.addMonsterToArea(area1.getId(), monster1.getId());
		areaService.addMonsterToArea(area1.getId(), monster2.getId());
		areaService.addMonsterToArea(area3.getId(), monster3.getId());
		monsterService.addWeaponToMonster(weapon1.getId(), monster2.getId());
		monsterService.addWeaponToMonster(weapon2.getId(), monster1.getId());
		monsterService.addWeaponToMonster(weapon2.getId(), monster3.getId());
		userService.addWeaponToUser(weapon1.getId(), user3.getId());
		userService.addWeaponToUser(weapon2.getId(), user1.getId());
		userService.addWeaponToUser(weapon3.getId(), user2.getId());
		userService.addWeaponToUser(weapon2.getId(), user2.getId());
		userService.addWeaponToUser(weapon4.getId(), admin.getId());


	}
}
