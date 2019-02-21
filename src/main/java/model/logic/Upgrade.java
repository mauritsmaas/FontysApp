package model.logic;

import javax.persistence.*;

@Entity
@Table(name = "upgrades")
public class Upgrade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Long boost;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getBoost() {
        return boost;
    }

    public void setBoost(Long boost) {
        this.boost = boost;
    }
}
