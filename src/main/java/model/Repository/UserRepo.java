package model.Repository;

import model.logic.User;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Local
@Stateless
public class UserRepo {
    @PersistenceContext(unitName = "myPU")
    EntityManager em;

    public void create(User user){
        try {
            em.persist(user);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public User getById(Long id) {
        try {
            return em.find(User.class, id);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public List<User> All(){
        List<User> users;
        try {
            users = em.createQuery(
                    "SELECT c FROM User c", User.class).getResultList();
            return users;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public void update(User user) {
        try {
            em.merge(user);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Long id) {
        try {
            User user = em.find(User.class, id);
            em.remove(user);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
