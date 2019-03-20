package model.Services;
import Auth.AuthenticationUtils;
import com.auth0.jwt.interfaces.DecodedJWT;
import model.Repository.ClubRepo;
import model.Repository.UserRepo;
import model.logic.Club;
import model.logic.User;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.HashMap;
import java.util.Map;

@Local
@Stateless
public class AuthService extends BaseService {

    @EJB
    JWTService jwtService;

    @EJB
    UserRepo userRepository;

    @EJB
    ClubRepo userRepo;

    public boolean register (User user) {
        if (user == null)
            return false;

        if (!user.validForRegistration())
            return false;

        try {
            // encode password with SHA256
            user.setPassword(AuthenticationUtils.encodeSHA256(user.getPassword()));
        } catch (Exception e) {
            logger.warning(e.getMessage());
            return false;
        }

        Club club = new Club(user.getClubname(), user);
        user.setClub(club);
        userRepository.create(user);
        userRepo.createClub(club);

        return true;
    }

    public String login (String username, String password) {
        if (username.isEmpty() || password.isEmpty())
            return null;

        try {
            // encode password with SHA256
            password = AuthenticationUtils.encodeSHA256(password);
        } catch (Exception e) {
            logger.warning(e.getMessage());
            return null;
        }

        User u = getByUsernameAndPassword(username, password);

        if (u == null)
            return null;

        // create JWT token
        return jwtService.createJWT(u);
    }

    public User getById(Long id) {
        return userRepository.getById(id);
    }

        public User getByUsernameAndPassword(String username, String password) {
        return userRepository.getByUsernameAndPassword(username, password);
    }
}
