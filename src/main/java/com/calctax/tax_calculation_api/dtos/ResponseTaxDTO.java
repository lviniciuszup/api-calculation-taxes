package com.calctax.tax_calculation_api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ResponseTaxDTO {
    private Long taxId;
    private String name;
    private String description;
    private BigDecimal baseValue;
    private BigDecimal aliquot;
}
