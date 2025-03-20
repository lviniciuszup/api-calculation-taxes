package com.calctax.tax_calculation_api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestForCalculatedTaxDTO {
    private Long taxId;
    private BigDecimal baseValue;
}
