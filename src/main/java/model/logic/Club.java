package model.logic;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.util.ArrayList;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = "Club.getClubByUserId", query = "select c from Club c where user_id = :id"),
}
)
@Entity
@Table(name = "clubs")
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @PositiveOrZero
    private Long balance;
//    @OneToMany(targetEntity = Player.class)
//    @LazyCollection(LazyCollectionOption.FALSE)
//    private List<Player> players = new ArrayList<>();

    @JsonbTransient
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "clubs_players",
            joinColumns = @JoinColumn(name = "Club_id"),
            inverseJoinColumns = @JoinColumn(name = "players_id")
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Player> players = new ArrayList<>();

    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Upgrade> upgrades = new ArrayList<>();

    @OneToOne
    private MatchSetup matchSetup;

    @OneToOne
    private User user;

    public Club(){}

    public Club(String clubname, User user){
        this.name = clubname;
        this.user = user;
        balance = Long.valueOf(5000);
    }

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

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public void addBalance(Long amount) {
        this.balance += amount;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void addPlayer(Player player){players.add(player);}

    public List<Upgrade> getUpgrades() {
        return upgrades;
    }

    public void setUpgrades(List<Upgrade> upgrades) {
        this.upgrades = upgrades;
    }

    public MatchSetup getMatchSetup() {
        return matchSetup;
    }

    public void setMatchSetup(MatchSetup matchSetup) {
        this.matchSetup = matchSetup;
    }

    @JsonbTransient
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
