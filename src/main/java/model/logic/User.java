package model.logic;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = User.FIND_ALL, query = "select b from User b"),
        @NamedQuery(name = "findUserById", query = "SELECT u FROM User u WHERE u.username = :username"),
        @NamedQuery(name = "User.getByUsernameAndPassword", query = "select u from User u where u.username = :username and u.password = :password"),
}
)
public class User implements Serializable {

    public static final String FIND_ALL = "User.FindAll";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String username;
    private String password;
    private String clubname;

    @OneToOne
    private Club club;

    public User() {    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public String getClubname() {
        return clubname;
    }

    public void setClubname(String clubname) {
        this.clubname = clubname;
    }

    public boolean validForRegistration () {
        return !this.username.isEmpty() && !this.password.isEmpty() && !this.clubname.isEmpty();
    }

    @Override
    public String toString() {
        return "User: "+ username + " - " + password;
    }
}
