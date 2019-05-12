package model.Repository;

import model.logic.Club;
import model.logic.Player;

import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

@Local
@Stateless
public class ClubRepo extends BaseRepo{

    private PlayerRepo playerRepo;

    public void createClub(Club club){
        try {
            em.persist(club);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public Club getById(Long id) {
        try {
            return em.find(Club.class, id);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public void update(Club club){
        try {
            em.merge(club);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public List<Club> all(){
        List<Club> clubs;
        try {
            clubs = em.createQuery(
                    "SELECT c FROM Club c", Club.class).getResultList();
            return clubs;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public Player contract(Long clubid, String rate){
        Player player = getPlayerByRating(rate);
        if (player != null){
            try {
                Club club = em.find(Club.class, clubid);
                club.addPlayer(player);
                player.addClub(club);
                em.merge(club);
                em.merge(player);
                return player;
            }catch (Exception ex){
                ex.printStackTrace();
                return null;
            }
        }
        return null;

    }

    private Player getPlayerByRating(String rate) {
        Player player = null;
        try {
            if(rate.equals("low")){
                System.out.println("LOW");
                List<Player> lowplayers = em.createQuery(
                        "select p from Player p " +
                                "where p.rating < 75")
                        .getResultList();
                player = lowplayers.get(new Random().nextInt(lowplayers.size()));
                return player;

            }else if(rate.equals("medium")){
                System.out.println("medium");
                List<Player> mediumplayers = em.createQuery(
                        "select p from Player p " +
                                "where p.rating BETWEEN 74 AND 85")
                        .getResultList();
                player = mediumplayers.get(new Random().nextInt(mediumplayers.size()));
                return player;
            }else if(rate.equals("high")){
                System.out.println("high");
                List<Player> highplayers = em.createQuery(
                        "select p from Player p " +
                                "where p.rating >= 85")
                        .getResultList();
                player = highplayers.get(new Random().nextInt(highplayers.size()));
                return player;
            }
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
        return player;
    }

    public Club getClubByUserId(Long id) {
        try {
            return em.createNamedQuery("Club.getClubByUserId", Club.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            logger.warning(e.getMessage());
            return null;
        }
    }

    public Club sell(Long clubid, Long playerid) {
        List<Player> players;
        Player deletedPlayer = null;
        Club club = null;
        try {
            deletedPlayer = em.find(Player.class, playerid);
            club =  em.find(Club.class, clubid);
            players = club.getPlayers();

            for (Iterator<Player> iterator = players.iterator(); iterator.hasNext(); ) {
                Player value = iterator.next();
                if (value.getId() == playerid) {
                    iterator.remove();
                    break;
                }
            }

            club.addBalance(deletedPlayer.getPrice());
            em.merge(club);
            return club;
        }catch (Exception ex){
            System.out.println("ERRORRR");
            ex.printStackTrace();
            System.out.println(ex.toString());
            return null;
        }
    }
}
