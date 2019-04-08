package model.Repository;

import model.logic.Club;
import model.logic.Player;

import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;

@Local
@Stateless
public class ClubRepo extends BaseRepo{

    public void createClub(Club club){
        try {
            em.persist(club);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public Club getById(Long id) {
        try {
            return em.find(Club.class, id);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public void update(Club club){
        try {
            em.merge(club);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public List<Club> all(){
        List<Club> clubs;
        try {
            clubs = em.createQuery(
                    "SELECT c FROM Club c", Club.class).getResultList();
            return clubs;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public void contract(Long clubid, Long playerid){
        try {
            Club club = em.find(Club.class, clubid);
            Player player = em.find(Player.class, playerid);
            club.addPlayer(player);
            player.addClub(club);
            em.merge(club);
            em.merge(player);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public Club getClubByUserId(Long id) {
        try {
            return em.createNamedQuery("Club.getClubByUserId", Club.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            logger.warning(e.getMessage());
            return null;
        }
    }
}
