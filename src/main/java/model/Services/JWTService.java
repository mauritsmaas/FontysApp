package model.Services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import model.logic.User;

import javax.ejb.Local;
import javax.ejb.Stateless;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;

@Local
@Stateless
public class JWTService {
    private static final String issuer = "soccergame";
    private static final int MINUTES = 20;
    private JWTVerifier verifier;

    public JWTService() {
        verifier = JWT.require(createAlgoritm())
                .withIssuer(issuer)
                .build();
    }

    public String createJWT(User u) throws JWTCreationException {
        return JWT.create()
                .withIssuer(issuer)
                .withClaim("id", u.getId())
                .withClaim("email", u.getEmail())
                .withExpiresAt(getExpireDate(MINUTES))
                .sign(createAlgoritm());
    }

    public void verifyJWT(String token) throws JWTVerificationException {
        DecodedJWT decoded = verifier.verify(token);

        if (decoded.getClaim("email").isNull())
            throw new JWTVerificationException("Invalid claim data");

        // expired
        if (decoded.getExpiresAt().compareTo(new Date()) < 0)
            throw new JWTVerificationException("Token expired");
    }

    private Date getExpireDate(int minutesToAdd) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MINUTE, minutesToAdd);
        return c.getTime();
    }

    private Algorithm createAlgoritm(){

        byte[] key = new byte[0];
        try {
            key = "Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=".getBytes("UTF-8");
            Algorithm algorithm = Algorithm.HMAC256(key);
            return algorithm;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
