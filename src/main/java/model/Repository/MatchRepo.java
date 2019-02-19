package model.Repository;

import model.logic.Match;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Local
@Stateless
public class MatchRepo {
    @PersistenceContext(unitName = "myPU")
    EntityManager em;

    public void create(Match match){
        try {
            em.persist(match);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public Match getById(Long id) {
        try {
            return em.find(Match.class, id);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public List<Match> All(){
        List<Match> matchs;
        try {
            matchs = em.createQuery(
                    "SELECT c FROM Match c", Match.class).getResultList();
            return matchs;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public void update(Match match) {
        try {
            em.merge(match);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Long id) {
        try {
            Match match = em.find(Match.class, id);
            em.remove(match);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
