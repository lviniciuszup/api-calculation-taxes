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

    public String getTaxName() {
        return taxName;
    }

    public void setTaxName(String taxName) {
        this.taxName = taxName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getBaseValue() {
        return baseValue;
    }

    public void setBaseValue(BigDecimal baseValue) {
        this.baseValue = baseValue;
    }

    public BigDecimal getAliquot() {
        return aliquot;
    }

    public Long getTaxId() {
        return taxId;
    }

    public void setTaxId(Long taxId) {
        this.taxId = taxId;
    }

    public void setAliquot(BigDecimal aliquot) {
        this.aliquot = aliquot;
    }
}

