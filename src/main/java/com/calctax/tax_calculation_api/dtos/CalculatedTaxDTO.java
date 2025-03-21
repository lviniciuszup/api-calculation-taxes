package com.calctax.tax_calculation_api.dtos;

import java.math.BigDecimal;

public class CalculatedTaxDTO {

    private String name;
    private BigDecimal baseValue;
    private BigDecimal aliquot;
    private BigDecimal calculatedValue;

    public CalculatedTaxDTO(String name, BigDecimal baseValue, BigDecimal aliquot, BigDecimal calculatedValue) {
        this.name = name;
        this.baseValue = baseValue;
        this.aliquot = aliquot;
        this.calculatedValue = calculatedValue;
    }
    public CalculatedTaxDTO() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setAliquot(BigDecimal aliquot) {
        this.aliquot = aliquot;
    }

    public BigDecimal getCalculatedValue() {
        return calculatedValue;
    }

    public void setCalculatedValue(BigDecimal calculatedValue) {
        this.calculatedValue = calculatedValue;
    }
}
