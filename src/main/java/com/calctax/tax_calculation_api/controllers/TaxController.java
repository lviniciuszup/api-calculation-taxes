package com.calctax.tax_calculation_api.controllers;

import com.calctax.tax_calculation_api.dtos.CalculatedTaxDTO;
import com.calctax.tax_calculation_api.dtos.RegisterTaxDTO;
import com.calctax.tax_calculation_api.dtos.ResponseTaxDTO;
import com.calctax.tax_calculation_api.services.TaxServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tax")
public class TaxController {
    private final TaxServices taxServices;

    @PostMapping("/tipos")
    public ResponseEntity<ResponseTaxDTO> newTax(@RequestBody @Valid RegisterTaxDTO registerTaxDTO){
        ResponseTaxDTO createdTax = taxServices.registerTax(registerTaxDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTax);
    }
    @PostMapping("/calculo")
    public ResponseEntity<CalculatedTaxDTO> finalValue(@RequestBody @Valid ResponseTaxDTO responseTaxDTO){
        CalculatedTaxDTO finalValueTax = taxServices.calculatedTax(responseTaxDTO);
        return ResponseEntity.ok(finalValueTax);
    }

    @GetMapping("/tipos")
    public ResponseEntity<List<ResponseTaxDTO>> listTaxes(){
        List<ResponseTaxDTO> allTaxes = taxServices.getAllTaxes();
        return ResponseEntity.ok(allTaxes);
    }

    @GetMapping("/tipos/{id}")
    public ResponseEntity<ResponseTaxDTO> listTaxesById(@PathVariable("id") Long taxId){
        ResponseTaxDTO tax = taxServices.getTaxById(taxId);
        return ResponseEntity.ok(tax);
    }

    @DeleteMapping("/tipos/{id}")
    public ResponseEntity<Void> deleteTaxById(@PathVariable("id") Long taxId){
        taxServices.deleteTaxById(taxId);
        return ResponseEntity.noContent().build();
    }
}
