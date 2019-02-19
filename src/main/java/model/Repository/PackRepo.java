package model.Repository;

import model.logic.Pack;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Local
@Stateless
public class PackRepo {
    @PersistenceContext(unitName = "myPU")
    EntityManager em;

    public void create(Pack pack){
        try {
            em.persist(pack);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public Pack getById(Long id) {
        try {
            return em.find(Pack.class, id);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public List<Pack> All(){
        List<Pack> packs;
        try {
            packs = em.createQuery(
                    "SELECT c FROM Pack c", Pack.class).getResultList();
            return packs;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public void update(Pack pack) {
        try {
            em.merge(pack);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Long id) {
        try {
            Pack pack = em.find(Pack.class, id);
            em.remove(pack);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
