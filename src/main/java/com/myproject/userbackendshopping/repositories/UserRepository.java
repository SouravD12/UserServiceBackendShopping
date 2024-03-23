package com.myproject.userbackendshopping.repositories;

import com.myproject.userbackendshopping.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User save(User user);
    Optional<User> findByEmail(String email);


}
