package model.logic;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    @Min(40)
    @Max(99)
    private Long rating;

    @Positive
    private Long price;

    @ManyToMany(mappedBy = "players")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Club> clubs = new ArrayList<>();

    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Link> links = new ArrayList<>();

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

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
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

    public void addClub(Club club){this.clubs.add(club);}

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public void addLink(String uri, String rel, String method){
        Link link = new Link();
        link.setLink(uri);
        link.setRel(rel);
        link.setMethod(method);
        links.add(link);
    }
}
