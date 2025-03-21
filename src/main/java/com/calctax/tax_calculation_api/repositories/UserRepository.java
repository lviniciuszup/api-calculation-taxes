package com.calctax.tax_calculation_api.repositories;

import com.calctax.tax_calculation_api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User>findByUsername(String username);
    boolean existsByUsername(String username);
}
