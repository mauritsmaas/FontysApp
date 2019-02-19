package model.Repository;

import model.logic.Club;
import model.logic.Player;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Local
@Stateless
public class ClubRepo {
    @PersistenceContext(unitName = "myPU")
    EntityManager em;

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

    }
}
