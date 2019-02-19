package model.Repository;

import model.logic.Upgrade;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Local
@Stateless
public class UpgradeRepo {
    @PersistenceContext(unitName = "myPU")
    EntityManager em;

    public void create(Upgrade upgrade){
        try {
            em.persist(upgrade);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public Upgrade getById(Long id) {
        try {
            return em.find(Upgrade.class, id);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public List<Upgrade> All(){
        List<Upgrade> upgrades;
        try {
            upgrades = em.createQuery(
                    "SELECT c FROM Upgrade c", Upgrade.class).getResultList();
            return upgrades;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public void update(Upgrade upgrade) {
        try {
            em.merge(upgrade);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Long id) {
        try {
            Upgrade upgrade = em.find(Upgrade.class, id);
            em.remove(upgrade);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
