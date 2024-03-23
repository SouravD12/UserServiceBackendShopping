package com.myproject.userbackendshopping.repositories;

import com.myproject.userbackendshopping.models.Tokens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Tokens,Long> {
    Tokens save (Tokens tokens);
    Optional<Tokens>findByValueAndDeleted(String value, boolean isDeleted);

    Optional<Tokens>findByValueAndDeletedAndExpiryAtGreaterThan(String value, boolean isDeleted,
                                                                Date expiryGreaterThan );
}
