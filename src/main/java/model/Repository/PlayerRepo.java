package model.Repository;

import model.Interceptors.SoccergameInterceptor;
import model.logic.Player;
import model.logic.User;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Local
@Stateless
public class PlayerRepo extends BaseRepo{

    private ArrayList<User> userList = new ArrayList<>();

    public List<Player> All(){
        List<Player> players;
        try {
            players = em.createQuery(
                    "SELECT u FROM Player u", Player.class).getResultList();
            return players;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Interceptors(SoccergameInterceptor.class)
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

    public void update(Player player){
        try {
            em.merge(player);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public List<Player> getPlayersByClubId(Long id) {
        List<Player> players;
        try {
            players = em.createQuery(
                    "select p from Player p " +
                            "inner join p.clubs clubs " +
                            "where clubs.id in :id")
                    .setParameter("id", id)
                    .getResultList();
            System.out.println(players.size());
            return players;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }

//        try {
//            return em.createNamedQuery("Player.getPlayerByClubId", Player.class)
//                    .setParameter("id", id)
//                    .getResultList();
//        } catch (Exception e) {
//            logger.warning(e.getMessage());
//            return null;
//        }
    }
}
