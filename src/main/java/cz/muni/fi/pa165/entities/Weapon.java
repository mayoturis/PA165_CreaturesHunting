package cz.muni.fi.pa165.entities;

import cz.muni.fi.pa165.enums.Ammunition;

import javax.persistence.*;

/**
 * Created by Marek on 25.10.2016.
 */
@Entity
@Table(name = "Weapons")
public class Weapon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int range;


    private Ammunition ammunition;

//    @OneToMany
//    @JoinColumn(name = "id")
//    private User user;
//
//    @ManyToMany
//    private List<Monster> monsters;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Weapon weapon = (Weapon) o;

        if (getId() != weapon.getId()) return false;
        if (getRange() != weapon.getRange()) return false;
        return getName().equals(weapon.getName());

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getRange();
        return result;
    }

    public Weapon() {
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

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

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
}
