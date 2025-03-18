package com.calctax.tax_calculation_api.services;

import com.calctax.tax_calculation_api.dtos.*;

import java.util.List;

public interface TaxServices {
    ResponseTaxDTO registerTax(RegisterTaxDTO registerTaxDTO);
    ResponseTaxDTO getTaxById(Long id);
    CalculatedTaxDTO calculatedTax(ResponseTaxDTO responseTaxDTO);
    List<ResponseTaxDTO> getAllTaxes();
    void deleteTaxById(Long id);
}
