package com.calctax.tax_calculation_api.repositories;

import com.calctax.tax_calculation_api.models.Tax;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxRepository extends JpaRepository<Tax, Long> {
}
