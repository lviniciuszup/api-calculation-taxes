package com.calctax.tax_calculation_api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalculatedTaxDTO {

    private String name;
    private BigDecimal baseValue;
    private BigDecimal aliquot;
    private BigDecimal calculatedValue;
}
