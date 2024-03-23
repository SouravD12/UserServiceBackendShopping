package com.myproject.userbackendshopping.controllers;

import com.myproject.userbackendshopping.Exceptions.UserNotFoundException;
import com.myproject.userbackendshopping.dtos.LoginRequestDto;
import com.myproject.userbackendshopping.dtos.LogoutRequestDto;
import com.myproject.userbackendshopping.dtos.SignUpRequestDto;
import com.myproject.userbackendshopping.dtos.UserDto;
import com.myproject.userbackendshopping.models.Tokens;
import com.myproject.userbackendshopping.models.User;
import com.myproject.userbackendshopping.services.UserService;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/logout")
    public ResponseEntity<Void>logout(@RequestBody LogoutRequestDto request){
//        Delete token if exixts , return 200 if exists else give 404 error
        userService.logOut(request.getToken());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/validate/{token}")
    public UserDto validateToken(@PathVariable("token") @NonNull String token){
        return UserDto.from(userService.validateToken(token));
    }
}

//Logic will be in services