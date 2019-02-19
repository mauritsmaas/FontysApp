package model.Services;

import model.Repository.ClubRepo;
import model.Repository.UpgradeRepo;
import model.logic.Club;
import model.logic.Upgrade;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class UpgradeService {

    @EJB
    private UpgradeRepo upgradeRepo;

    @PostConstruct
    public void startWorking(){
        System.out.println("start working.....");
    }

    public void create(Upgrade upgrade){
        upgradeRepo.create(upgrade);}

    public Upgrade getById(Long id){return upgradeRepo.getById(id);}

    public List<Upgrade> All() {
        return upgradeRepo.All();
    }

    public void update(Upgrade upgrade){
        upgradeRepo.update(upgrade);
    }

    public void delete(Long id){upgradeRepo.delete(id);}
}
