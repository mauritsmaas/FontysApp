package model.logic;

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
public class DatabaseProvider {

    @PersistenceContext(unitName = "myPU")
    EntityManager em;

    private ArrayList<User> userList = new ArrayList<>();

    @PostConstruct
    public void pietje_init() {
        userList.add(new User("local", "user"));
        userList.add(new User("hard", "coded"));
    }

    public ArrayList<User> getUsers(){
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jdbc/PayaraPool");
            Connection conn = ds.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
            while (rs.next()){
                User u = new User(rs.getString("username"),rs.getString("password"));
                userList.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return userList;
    }

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

    public void persistUser(User user){
        try {
            em.persist(user);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

    }

}
