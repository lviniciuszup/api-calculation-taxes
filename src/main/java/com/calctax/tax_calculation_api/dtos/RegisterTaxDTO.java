package com.calctax.tax_calculation_api.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class RequestCalculationTaxDTO {

    @NotEmpty(message = "Name cannot be empty")
    private String name;
    @NotEmpty(message = "Description cannot be empty")
    private String description;
    @NotNull(message = "Aliquota cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "A Alíquota precisa ser maior que zero")
    private BigDecimal aliquot;
}
