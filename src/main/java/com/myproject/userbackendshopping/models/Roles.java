package com.myproject.userbackendshopping.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Roles extends Base {
    private String name;
}
