package model.Repository;

import model.logic.MatchSetup;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Local
@Stateless
public class MatchSetupRepo {
    @PersistenceContext(unitName = "myPU")
    EntityManager em;

    public void create(MatchSetup matchSetup){
        try {
            em.persist(matchSetup);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public MatchSetup getById(Long id) {
        try {
            return em.find(MatchSetup.class, id);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public List<MatchSetup> All(){
        List<MatchSetup> matchSetups;
        try {
            matchSetups = em.createQuery(
                    "SELECT c FROM MatchSetup c", MatchSetup.class).getResultList();
            return matchSetups;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public void update(MatchSetup matchSetup) {
        try {
            em.merge(matchSetup);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Long id) {
        try {
            MatchSetup matchSetup = em.find(MatchSetup.class, id);
            em.remove(matchSetup);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
