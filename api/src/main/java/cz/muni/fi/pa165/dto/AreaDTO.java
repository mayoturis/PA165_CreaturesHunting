package cz.muni.fi.pa165.dto;

import cz.muni.fi.pa165.enums.DangerLevel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Data transfer object for area.
 *
 * @author Michael Cada
 */
public class AreaDTO {

	private int id;
	private String name;
	private String description;
	private BigDecimal size;
	private DangerLevel dangerLevel;

	private List<MonsterDTO> monsters = new ArrayList<MonsterDTO>();

	public AreaDTO(String name, DangerLevel dangerLevel, BigDecimal size) {
		this.name = name;
		this.dangerLevel = dangerLevel;
		this.size = size;
	}

	public AreaDTO() {
	}

	public void addMonster(MonsterDTO newMonster) {
		this.monsters.add(newMonster);
		newMonster.addArea(this);
	}

	public int getId() { return id; }

	public void setId(int id) {
		this.id = id;
	}

	public DangerLevel getDangerLevel() { return dangerLevel; }

	public void setDangerLevel(DangerLevel dangerLevel) { this.dangerLevel = dangerLevel; }

	public String getName() { return name; }

	public void setName(String name) { this.name = name; }

	public String getDescription() { return description; }

	public void setDescription(String description) { this.description = description; }

	public BigDecimal getSize() { return size; }

	public void setSize(BigDecimal size) { this.size = size; }

	public List<MonsterDTO> getMonsters() { return monsters; }

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof AreaDTO)) return false;

		AreaDTO area = (AreaDTO) o;

		return name.equals(area.getName());
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}
}
