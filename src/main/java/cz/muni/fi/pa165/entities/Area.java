package cz.muni.fi.pa165.entities;

import cz.muni.fi.pa165.enums.DangerLvl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
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
@Entity
public class Area {

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
    private DangerLvl dangerLvl;

	@ManyToMany(mappedBy = "Area")
	private List<Monster> monsters = new ArrayList<Monster>();

    public void addMonster(Monster newMonster) {
        this.monsters.add(newMonster);
    }

    public int getId() { return id; }

    public DangerLvl getDangerLvl() { return dangerLvl; }

    public void setDangerLvl(DangerLvl dangerLvl) { this.dangerLvl = dangerLvl; }

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
