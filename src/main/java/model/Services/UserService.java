package model.Services;

import model.Repository.UserRepo;
import model.logic.User;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class UserService {

    @EJB
    private UserRepo userRepo;

    @PostConstruct
    public void startWorking(){
        System.out.println("start working.....");
    }

    public void create(User user){
        userRepo.create(user);
    }

    public User getById(Long id){return userRepo.getById(id);}

    public List<User> All() {
        return userRepo.All();
    }

    public void update(User user){
        userRepo.update(user);
    }

    public void delete(Long id){userRepo.delete(id);}
}
