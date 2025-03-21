package com.calctax.tax_calculation_api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ResponseTaxDTO {
    private Long taxId;
    private String name;
    private String description;
    private BigDecimal aliquot;

    public ResponseTaxDTO(Long taxId, String name, String description, BigDecimal aliquot) {
        this.taxId = taxId;
        this.name = name;
        this.description = description;
        this.aliquot = aliquot;
    }

    public Long getTaxId() {
        return taxId;
    }

    public void setTaxId(Long taxId) {
        this.taxId = taxId;
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
