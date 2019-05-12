package model.Services;
import Auth.AuthenticationUtils;
import model.Repository.ClubRepo;
import model.Repository.UserRepo;
import model.logic.Club;
import model.logic.User;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import java.util.Random;

@Local
@Stateless
public class AuthService extends BaseService {

    private static final int MIN_VERIFICATION_CODE = 999999;
    private static final int MAX_VERIFICATION_CODE = 100000;

    @EJB
    JWTService jwtService;

    @EJB
    UserRepo userRepository;

    @EJB
    ClubRepo clubRepo;

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
        user.setVerification_code(Long.valueOf(0));
        userRepository.create(user);
        clubRepo.createClub(club);

        return true;
    }

    public String login (String email, String password) {
        if (email.isEmpty() || password.isEmpty())
            return null;

        try {
            // encode password with SHA256
            password = AuthenticationUtils.encodeSHA256(password);
        } catch (Exception e) {
            logger.warning(e.getMessage());
            return null;
        }

        User u = getByUsernameAndPassword(email, password);

        if (u == null)
            return null;

        u.setVerification_code(generateVerificationCode());

        userRepository.update(u);

        try {
            sendEmail(u);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return u.getEmail();
    }

    public User getById(Long id) {
        return userRepository.getById(id);
    }

    public User getByUsernameAndPassword(String username, String password) {
        return userRepository.getByUsernameAndPassword(username, password);
    }

    public Long generateVerificationCode() {
        Random rand = new Random();
        Integer code = rand.nextInt(MIN_VERIFICATION_CODE
                - MAX_VERIFICATION_CODE + 1) + MAX_VERIFICATION_CODE;
        return Long.valueOf(code);
    }

    public void sendEmail(User user) throws MessagingException {

        String username = "mauritsmaasje@gmail.com";
        String password = "Maurits28";

         Properties props = new Properties();
         props.put("mail.smtp.auth", "true");
         props.put("mail.smtp.starttls.enable", "true");
         props.put("mail.smtp.host", "smtp.gmail.com");
         props.put("mail.smtp.port", "587");

         Session session = Session.getInstance(props,
                 new Authenticator() {
                     protected PasswordAuthentication getPasswordAuthentication() {
                         return new PasswordAuthentication(username, password);
                     }
                 });

         try {

             Message message = new MimeMessage(session);
             message.setFrom(new InternetAddress(username));
             message.setRecipients(Message.RecipientType.TO,
                     InternetAddress.parse(user.getEmail()));
             message.setSubject("Verification SoccerGame");
             message.setText("Dear User,"
                     + "\n\n This is your verification code: "+ user.getVerification_code());

             Transport.send(message);

             System.out.println("Done");

         } catch (MessagingException e) {
             throw new RuntimeException(e);
         }

    }

    public String checkCode(User user) {
        User u = userRepository.verifyCode(user);
        if ( u != null){
            return jwtService.createJWT(u);
        }
        return null;

    }
}
