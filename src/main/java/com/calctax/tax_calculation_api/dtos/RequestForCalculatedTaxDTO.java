package com.calctax.tax_calculation_api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class RequestForCalculatedTaxDTO {
    private Long taxId;
    private BigDecimal baseValue;

    public RequestForCalculatedTaxDTO(Long taxId, BigDecimal baseValue) {
        this.taxId = taxId;
        this.baseValue = baseValue;
    }

    public Long getTaxId() {
        return taxId;
    }

    public void setTaxId(Long taxId) {
        this.taxId = taxId;
    }

    public BigDecimal getBaseValue() {
        return baseValue;
    }

    public void setBaseValue(BigDecimal baseValue) {
        this.baseValue = baseValue;
    }
}
