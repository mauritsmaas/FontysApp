package model.Repository;

import model.logic.Player;
import model.logic.User;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Local
@Stateless
public class PlayerRepo{
    @PersistenceContext(unitName = "myPU")
    EntityManager em;

    private ArrayList<User> userList = new ArrayList<>();

    public List<User> allUsers(){
        List<User> userList = null;
        try {
            userList = em.createQuery(
                    "SELECT u FROM User u", User.class).getResultList();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return userList;
    }

    public void createPlayer(Player player){
        try {
            em.persist(player);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public Player getById(Long id) {
        try {
            return em.find(Player.class, id);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
