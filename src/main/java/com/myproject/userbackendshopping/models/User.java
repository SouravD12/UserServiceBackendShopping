package com.myproject.userbackendshopping.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class User extends Base{
    private String name;
    private String email;
    private String hashedPassword;
    @ManyToMany
    private List<Roles> roles;
    private boolean isVerified;


}
