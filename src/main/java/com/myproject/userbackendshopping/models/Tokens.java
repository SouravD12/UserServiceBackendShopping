package com.myproject.userbackendshopping.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Tokens extends Base {
    private String value;
    @ManyToOne
    private User user;
    private Date expiryAt;


}
