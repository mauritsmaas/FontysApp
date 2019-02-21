package model.Services;

import model.Repository.MatchSetupRepo;
import model.logic.MatchSetup;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class MatchSetupService {

    @EJB
    private MatchSetupRepo matchSetupRepo;

    @PostConstruct
    public void startWorking(){
        System.out.println("start working.....");
    }

    public void create(MatchSetup matchSetup){
        matchSetupRepo.create(matchSetup);}

    public MatchSetup getById(Long id){return matchSetupRepo.getById(id);}

    public List<MatchSetup> All() {
        return matchSetupRepo.All();
    }

    public void update(MatchSetup matchSetup){
        matchSetupRepo.update(matchSetup);
    }

    public void delete(Long id){matchSetupRepo.delete(id);}
}
