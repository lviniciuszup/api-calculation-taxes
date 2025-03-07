package com.calctax.tax_calculation_api.repositories;

import com.calctax.tax_calculation_api.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
