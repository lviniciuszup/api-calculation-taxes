package com.calctax.tax_calculation_api.repositories;

import com.calctax.tax_calculation_api.models.Tax;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaxRepository extends JpaRepository<Tax, Long> {
    boolean existsByTaxName(String name);
    Optional<Tax>findTaxByTaxId(Long taxId);
}
