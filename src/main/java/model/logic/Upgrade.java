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
    @OneToOne
    private Pack pack;
    @ManyToOne
    private Player player;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pack getPack() {
        return pack;
    }

    public void setPack(Pack pack) {
        this.pack = pack;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
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
