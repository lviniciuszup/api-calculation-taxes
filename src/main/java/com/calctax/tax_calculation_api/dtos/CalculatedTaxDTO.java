package com.calctax.tax_calculation_api.dtos;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class CalculatedTaxDTO {

    private String name;
    private BigDecimal baseValue;
    private BigDecimal aliquot;
    private BigDecimal calculatedValue;
}
