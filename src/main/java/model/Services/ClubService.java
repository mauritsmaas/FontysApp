package model.Services;

import model.Repository.ClubRepo;
import model.Repository.PlayerRepo;
import model.logic.Club;
import model.logic.Player;
import model.logic.User;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class ClubService {

    @EJB
    private ClubRepo clubRepo;

    @PostConstruct
    public void startWorking(){
        System.out.println("start working.....");
    }

    public void createClub(Club club){
        clubRepo.createClub(club);}

    public Club getById(Long id){return clubRepo.getById(id);}

    public List<Club> all() {
        return clubRepo.all();
    }
}
