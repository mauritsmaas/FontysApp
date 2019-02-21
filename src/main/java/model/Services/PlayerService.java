package model.Services;

import model.Repository.PlayerRepo;
import model.logic.Player;
import model.logic.User;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class PlayerService {

    @EJB
    private PlayerRepo playerRepo;

    @PostConstruct
    public void startWorking(){
        System.out.println("start working.....");
    }

    public void createPlayer(Player player){
        playerRepo.createPlayer(player);}

    public List<Player> All(){ return playerRepo.All();}

    public Player getById(Long id){return playerRepo.getById(id);}

    public void update(Player player){playerRepo.update(player);}
}
