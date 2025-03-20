package com.calctax.tax_calculation_api.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class RegisterTaxDTO {

    @NotEmpty(message = "Nome não pode ser vázio")
    private String name;
    @NotEmpty(message = "Descrição não pode se vázia")
    private String description;
    @NotNull(message = "Aliquota não pode ser nulo")
    @DecimalMin(value = "0.0", inclusive = false, message = "A Alíquota precisa ser maior que zero")
    private BigDecimal aliquot;
}
