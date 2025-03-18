package com.calctax.tax_calculation_api.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TaxDTO {

    private String name;
    private String description;
    private BigDecimal baseValue;
    private BigDecimal aliquot;
    private BigDecimal calculatedValue;
}
