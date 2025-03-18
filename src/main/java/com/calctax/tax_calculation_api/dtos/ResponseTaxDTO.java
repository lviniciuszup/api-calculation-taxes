package com.calctax.tax_calculation_api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ResponseCalculationTaxDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal aliquot;
}
