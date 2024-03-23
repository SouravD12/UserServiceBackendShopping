package com.myproject.userbackendshopping.controllers;

import com.myproject.userbackendshopping.Exceptions.UserNotFoundException;
import com.myproject.userbackendshopping.dtos.LoginRequestDto;
import com.myproject.userbackendshopping.dtos.SignUpRequestDto;
import com.myproject.userbackendshopping.models.Tokens;
import com.myproject.userbackendshopping.models.User;
import com.myproject.userbackendshopping.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    private UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/login")
    public Tokens login(@RequestBody LoginRequestDto request) throws UserNotFoundException {
//        Check if email and password in db , if yes return user else throw error
        return userService.login(request.getEmail(),request.getPassword());
    }

    @PostMapping("/signUp")
    public User signUp(@RequestBody SignUpRequestDto request){
//        Store user as is in the db
        String email = request.getEmail();
        String name = request.getName();
        String password = request.getPassword();

        return userService.signUp(name,email,password);
    }
    public ResponseEntity<Void>logout(){
//        Delete token if exixts , return 200 if exists else give 404 error
        return null;
    }
}

//Logic will be in services