package com.calctax.tax_calculation_api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "taxes")
@AllArgsConstructor
@NoArgsConstructor
public class Tax {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taxId;
    @Column(nullable = false, unique = true)
    private String taxName;
    private String description;
    @Column(nullable = true)
    private BigDecimal baseValue;
    @Column(nullable = false)
    private BigDecimal aliquot;

    public Tax(String taxName, String description, BigDecimal aliquot) {
        this.taxName = taxName;
        this.description = description;
        this.aliquot = aliquot;
    }
}

