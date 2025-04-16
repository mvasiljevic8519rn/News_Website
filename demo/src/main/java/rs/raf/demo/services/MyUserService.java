package rs.raf.demo.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.apache.commons.codec.digest.DigestUtils;
import rs.raf.demo.entities.User;
import rs.raf.demo.repositories.Users.UserRepository;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

public class MyUserService {

    @Inject
    UserRepository userRepository;

    public String login(String username, String password)
    {
        String hashedPassword = DigestUtils.sha256Hex(password);

        User user = this.userRepository.findUser(username);
        if (user == null || !user.getHashedPassword().equals(hashedPassword)) {
            return null;
        }

        Date issuedAt = new Date();
        Date expiresAt = new Date(issuedAt.getTime() + 24*60*60*1000); // One day

        Algorithm algorithm = Algorithm.HMAC256("secret");

        // JWT-om mozete bezbedono poslati informacije na FE
        // Tako sto sve sto zelite da posaljete zapakujete u claims mapu


        return JWT.create()
                .withIssuedAt(issuedAt)
                .withExpiresAt(expiresAt)
                .withSubject(username)
                .withClaim("role", user.getTip())
                .withClaim("ime", user.getIme())
                .withClaim("prezime", user.getPrezime())
                .withClaim("id",user.getId())
                .withClaim("status", user.getAktivnost())
                .sign(algorithm);
    }

    public boolean isAuthorized(String token){
        Algorithm algorithm = Algorithm.HMAC256("secret");
        JWTVerifier verifier = JWT.require(algorithm)
                .build();
        DecodedJWT jwt = verifier.verify(token);

        String username = jwt.getSubject();
//        jwt.getClaim("role").asString();

        User user = this.userRepository.findUser(username);

        if (user == null){
            return false;
        }
//
        return true;
    }

    public  boolean checkEmail(String email){return  this.userRepository.checkEmail(email);}
    public User editUser(User user){return  this.userRepository.editUser(user);}
    public  User addUser(User user){return  this.userRepository.addUser(user);}
    public List<User> allUsers(){return  this.userRepository.allUsers();}

    public User getUser(Integer id){return  this.userRepository.getUser(id);}
    public void activateDeactivate(int id){ this.userRepository.ActivateDeactivate(id);}

    public void deleteUser(String email){this.userRepository.deleteUser(email);}
    public User findUser(String email){return  this.userRepository.findUser(email);}


}
