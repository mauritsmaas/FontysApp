package model.Services;

import model.Repository.MatchRepo;
import model.logic.Match;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class MatchService {

    @EJB
    private MatchRepo matchRepo;

    @PostConstruct
    public void startWorking(){
        System.out.println("start working.....");
    }

    public void create(Match match){
        matchRepo.create(match);}

    public Match getById(Long id){return matchRepo.getById(id);}

    public List<Match> All() {
        return matchRepo.All();
    }

    public void update(Match match){
        matchRepo.update(match);
    }

    public void delete(Long id){matchRepo.delete(id);}
}
