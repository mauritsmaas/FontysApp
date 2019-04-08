package model.logic;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = User.FIND_ALL, query = "select b from User b"),
        @NamedQuery(name = "findUserById", query = "SELECT u FROM User u WHERE u.email = :username"),
        @NamedQuery(name = "User.getByUsernameAndPassword", query = "select u from User u where u.email = :username and u.password = :password"),
        @NamedQuery(name = "User.getByEmailAndCode", query = "select u from User u where u.email = :email and u.verification_code = :code")
}
)
public class User implements Serializable {

    public static final String FIND_ALL = "User.FindAll";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Email
    private String email;


    private Long verification_code;
    private String password;
    private String clubname;

    @OneToOne
    private Club club;

    public User() {    }

    public User(String username, String password) {
        this.email = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getVerification_code() {
        return verification_code;
    }

    public void setVerification_code(Long verification_code) {
        this.verification_code = verification_code;
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
        return !this.email.isEmpty() && !this.password.isEmpty() && !this.clubname.isEmpty();
    }

    @Override
    public String toString() {
        return "User: "+ email + " - " + password;
    }
}
