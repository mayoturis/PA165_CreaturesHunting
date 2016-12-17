package cz.muni.fi.pa165.entities;

import cz.muni.fi.pa165.entities.base.Entity;
import cz.muni.fi.pa165.enums.DangerLevel;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Class used for storing information about area.
 * Each area has unique name and is furthermore specified by description.
 *
 * @author Michael Cada
 */
@javax.persistence.Entity
public class Area implements Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	@Column(nullable = false, unique = true)
	private String name;

	private String description;

	@NotNull
	@DecimalMin("0.1")
	private BigDecimal size;

	@NotNull
	private DangerLevel dangerLevel;

	@ManyToMany(mappedBy = "areas", fetch = FetchType.EAGER)
	private List<Monster> monsters = new ArrayList<>();

	protected Area() {
		// required by Hibernate
	}

	public Area(String name, DangerLevel dangerLevel, BigDecimal size) {
		this.name = name;
		this.dangerLevel = dangerLevel;
		this.size = size;
	}

	public void addMonster(Monster newMonster) {
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

	public List<Monster> getMonsters() { return monsters; }

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Area)) return false;

		Area area = (Area) o;

		return name.equals(area.getName());
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}
}
