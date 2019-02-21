package model.logic;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String score;
    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<MatchSetup> matchSetups;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public void setMatchSetups(List<MatchSetup> matchSetups) {
        this.matchSetups = matchSetups;
    }

    public List<MatchSetup> getMatchSetups() {
        return matchSetups;
    }
}
