package model.facade;

import model.logic.DatabaseProvider;
import model.logic.User;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class DatabaseFacade {

    @EJB
    private DatabaseProvider databaseProvider;

    @PostConstruct
    public void startWorking(){
        System.out.println("start working.....");
    }

    public ArrayList<User> getUsers(){ return databaseProvider.getUsers();}

    public void persistUser(User user){databaseProvider.persistUser(user);}

    public List<User> allUsers(){ return databaseProvider.allUsers();}
}
