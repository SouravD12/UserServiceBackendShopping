package com.myproject.userbackendshopping.repositories;

import com.myproject.userbackendshopping.models.Tokens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Tokens,Long> {
    Tokens save (Tokens tokens);
}
