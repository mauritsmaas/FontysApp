package model.logic;

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

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "player_club",
            joinColumns = @JoinColumn(name = "players_id"),
            inverseJoinColumns = @JoinColumn(name = "clubs_id")
    )
    private List<Club> clubs = new ArrayList<>();

    @ManyToMany(targetEntity = Match.class)
    private List<Match> matches;

    @OneToOne
    private Pack pack;

    @OneToMany( targetEntity=Upgrade.class )
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
