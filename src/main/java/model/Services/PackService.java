package model.Services;

import model.Repository.PackRepo;
import model.logic.Pack;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class PackService {

    @EJB
    private PackRepo packRepo;

    @PostConstruct
    public void startWorking(){
        System.out.println("start working.....");
    }

    public void create(Pack pack){
        packRepo.create(pack);}

    public Pack getById(Long id){return packRepo.getById(id);}

    public List<Pack> All() {
        return packRepo.All();
    }

    public void update(Pack pack){
        packRepo.update(pack);
    }

    public void delete(Long id){packRepo.delete(id);}
}
