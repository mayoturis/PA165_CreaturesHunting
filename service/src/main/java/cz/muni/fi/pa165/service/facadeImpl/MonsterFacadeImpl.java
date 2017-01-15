package cz.muni.fi.pa165.service.facadeImpl;

import cz.muni.fi.pa165.dto.MonsterDTO;
import cz.muni.fi.pa165.dto.WeaponDTO;
import cz.muni.fi.pa165.entities.Monster;
import cz.muni.fi.pa165.facade.MonsterFacade;
import cz.muni.fi.pa165.service.facadeImpl.base.CrudFacadeImpl;
import cz.muni.fi.pa165.service.services.MonsterService;
import cz.muni.fi.pa165.service.services.base.CrudService;
import cz.muni.fi.pa165.service.services.mapping.MappingService;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Monster facade implementation.
 *
 * @author Simona Kruppova
 */
@Named
@Transactional
public class MonsterFacadeImpl extends CrudFacadeImpl<MonsterDTO, Monster> implements MonsterFacade {

	private MonsterService monsterService;
	private MappingService mappingService;

	@Inject
	public MonsterFacadeImpl(MonsterService monsterService, MappingService mappingService) {
		super(monsterService, mappingService, MonsterDTO.class, Monster.class);
		this.monsterService = monsterService;
		this.mappingService = mappingService;
	}

	@Override
	public void addWeaponToMonster(int weaponId, int monsterId) {
		monsterService.addWeaponToMonster(weaponId, monsterId);
	}

	@Override
	public List<WeaponDTO> findWeaponsForMonster(int monsterId) {
		return mappingService.map(this.monsterService.findWeaponsForMonster(monsterId), WeaponDTO.class);
	}

	@Override
	public boolean monsterHasWeapon(int monsterId, int weaponId) {
		return monsterService.monsterHasWeapon(monsterId, weaponId);
	}

	@Override
	public void removeWeaponFromMonster(int weaponId, int monsterId) {
		monsterService.removeWeaponFromMonster(weaponId, monsterId);
	}
}
