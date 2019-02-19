package model.logic;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Long overallRating;
    private Long games;
    private Long price;

    @ManyToMany(targetEntity = Club.class)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Club> clubs = new ArrayList<>();

    @ManyToMany(targetEntity = Match.class)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Match> matches;

    @OneToOne
    private Pack pack;

    @OneToMany( targetEntity=Upgrade.class)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Upgrade> upgrades;

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

    public Long getOverallRating() {
        return overallRating;
    }

    public void setOverallRating(Long overallRating) {
        this.overallRating = overallRating;
    }

    public Long getGames() {
        return games;
    }

    public void setGames(Long games) {
        this.games = games;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public List<Club> getClubs() {
        return clubs;
    }

    public void setClubs(List<Club> clubs) {
        this.clubs = clubs;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }
}
