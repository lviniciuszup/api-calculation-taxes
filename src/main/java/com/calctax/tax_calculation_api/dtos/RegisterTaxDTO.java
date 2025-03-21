package com.calctax.tax_calculation_api.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class RegisterTaxDTO {

    @NotEmpty(message = "Nome não pode ser vázio")
    private String name;
    @NotEmpty(message = "Descrição não pode se vázia")
    private String description;
    @NotNull(message = "Aliquota não pode ser nulo")
    @DecimalMin(value = "0.0", inclusive = false, message = "A Alíquota precisa ser maior que zero")
    private BigDecimal aliquot;

    public RegisterTaxDTO(String name, String description, BigDecimal aliquot) {
        this.name = name;
        this.description = description;
        this.aliquot = aliquot;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAliquot() {
        return aliquot;
    }

    public void setAliquot(BigDecimal aliquot) {
        this.aliquot = aliquot;
    }
}
