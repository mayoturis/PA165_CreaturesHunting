package cz.muni.fi.pa165.entities;

import com.sun.istack.internal.NotNull;
import cz.muni.fi.pa165.enums.Ammunition;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Class created to store information about weapon.
 * @author Ondrej Zeman
 */
@Entity
public class Weapon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String name;

    @Min(0)
    private int range;

    @NotNull
    private Ammunition ammunition;

    @ManyToMany
    private Set<User> user = new HashSet<User>();

    @ManyToMany
    private Set<Monster> monsters = new HashSet<Monster>();

    public Weapon() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Weapon)) return false;

        Weapon weapon = (Weapon) o;

        return getName().equals(weapon.getName());

    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }

    public void setMonsters(Set<Monster> monsters) {
        this.monsters = monsters;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Ammunition getAmmunition() {
        return ammunition;
    }

    public void setAmmunition(Ammunition ammunition) {
        this.ammunition = ammunition;
    }

    public void addMonsters(List<Monster> monsters) {
        this.monsters.addAll(monsters);
    }

    public void addMonster(Monster monster) {
        this.monsters.add(monster);
    }


}
