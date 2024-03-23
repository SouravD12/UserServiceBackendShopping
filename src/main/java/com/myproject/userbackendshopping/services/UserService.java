package com.myproject.userbackendshopping.services;

import com.myproject.userbackendshopping.Exceptions.UserNotFoundException;
import com.myproject.userbackendshopping.models.User;
import com.myproject.userbackendshopping.models.Tokens;
import com.myproject.userbackendshopping.repositories.TokenRepository;
import com.myproject.userbackendshopping.repositories.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;


@Service
public class UserService {
    private UserRepository userRepository;
    private TokenRepository tokenRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    public UserService(UserRepository userRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder,
                       TokenRepository tokenRepository){
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    public User signUp(String name,
                       String email,
                       String password
                       ){
        User u = new User();
        u.setName(name);
        u.setEmail(email);
        u.setHashedPassword(bCryptPasswordEncoder.encode(password));
        User user = userRepository.save(u);
        return user;
    }

    public Tokens login(String email, String password)throws UserNotFoundException {
        Optional<User>userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()){
            throw new UserNotFoundException("User with " +email+" doesn't exist");
        }
        User user = userOptional.get();
        if (!bCryptPasswordEncoder.matches(password, user.getHashedPassword())){
//            Need to throw password doesn't match exception
            return null;
        }
        Tokens savedToken = getTokens(user);

        return savedToken;
    }

//    Extracting the token into a different method , to keep the login method clean
    private Tokens getTokens(User user) {
        LocalDate today = LocalDate.now();
        LocalDate thirtyDaysLater = today.plus(30, ChronoUnit.DAYS);
        Date expiryDate = Date.from(thirtyDaysLater.atStartOfDay(ZoneId.systemDefault()).toInstant());
//        Date expiryDate = Date.from(zonedExpiryDateTime.toInstant());
        Tokens tokens = new Tokens();
        tokens.setUser(user);
        tokens.setExpiryAt(expiryDate);
        tokens.setValue(RandomStringUtils.randomAlphanumeric(128));
//        Change it into JWT
        Tokens savedToken = tokenRepository.save(tokens);
        return savedToken;
    }

    public void logOut(String tokens){
        Optional<Tokens> tokens1 = tokenRepository.findByValueAndDeleted(tokens,false);
        if (tokens1.isEmpty()){
            System.out.println("Token expired");
        }
        Tokens tkn = tokens1.get();
        tkn.setDeleted(true);
        tokenRepository.save(tkn);
    }

    public User validateToken(String token){
        Optional<Tokens> tkn = tokenRepository.findByValueAndDeletedAndExpiryAtGreaterThan(token,false,
                new Date());
        if(tkn.isEmpty()){
            return null;
        }
//        Instead of validating via DB , validate using JWT
        return tkn.get().getUser();
    }
}


//Services don't take DTO's controllers take DTO's
//Spring Security only allows to make calls only if the people is logged in first