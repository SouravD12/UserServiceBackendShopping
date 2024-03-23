package com.myproject.userbackendshopping.dtos;

import com.myproject.userbackendshopping.models.Roles;
import com.myproject.userbackendshopping.models.User;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDto {
    private String name;
    private String email;
    @ManyToMany
    private List<Roles> roles;
    private boolean isEmailVerified;

    public static UserDto from(User user) {
        if (user == null) return null;

        UserDto userDto = new UserDto();
        userDto.email = user.getEmail();
        userDto.name = user.getName();
        userDto.roles = user.getRoles();
        userDto.isEmailVerified = user.isEmailVerified();

        return userDto;
        }
    }
