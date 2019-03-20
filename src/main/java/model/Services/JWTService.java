package model.Services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import model.logic.User;

import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@Local
@Stateless
public class JWTService {
    private static final String secret = "secret";
    private static final String issuer = "soccergame";
    private static final int DAYS_TO_ADD = 1;
    private static final Algorithm algorithm = Algorithm.HMAC256(secret);
    private JWTVerifier verifier;

    public JWTService() {
        verifier = JWT.require(algorithm)
                .withIssuer(issuer)
                .build();
    }

    public String createJWT(User u) throws JWTCreationException {
        return JWT.create()
                .withIssuer(issuer)
                .withClaim("username", u.getUsername())
                .withClaim("password", u.getPassword())
                .withExpiresAt(getExpireDate(DAYS_TO_ADD))
                .sign(algorithm);
    }

    public void verifyJWT(String token) throws JWTVerificationException {
        DecodedJWT decoded = verifier.verify(token);

        if (decoded.getClaim("username").isNull() || decoded.getClaim("password").isNull())
            throw new JWTVerificationException("Invalid claim data");

        // expired
        if (decoded.getExpiresAt().compareTo(new Date()) < 0)
            throw new JWTVerificationException("Token expired");
    }

    private Date getExpireDate(int daysToAdd) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, daysToAdd);
        return c.getTime();
    }
}

