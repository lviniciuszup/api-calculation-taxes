package com.calctax.tax_calculation_api.controllers;

import com.calctax.tax_calculation_api.dtos.CalculatedTaxDTO;
import com.calctax.tax_calculation_api.dtos.RegisterTaxDTO;
import com.calctax.tax_calculation_api.dtos.RequestForCalculatedTaxDTO;
import com.calctax.tax_calculation_api.dtos.ResponseTaxDTO;
import com.calctax.tax_calculation_api.services.TaxServices;
import com.calctax.tax_calculation_api.services.UserServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/taxes")
public class TaxController {
    private TaxServices taxServices;

    public TaxController (TaxServices taxServices){
        this.taxServices = taxServices;
    }

    @PostMapping("/tipos")
    public ResponseEntity<ResponseTaxDTO> createTax(@RequestBody @Valid RegisterTaxDTO registerTaxDTO){
        ResponseTaxDTO createdTax = taxServices.registerTax(registerTaxDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTax);
    }
    @PostMapping("/calculo")
    public ResponseEntity<CalculatedTaxDTO> calculateTax(@RequestBody @Valid RequestForCalculatedTaxDTO requestForCalculatedTaxDTO){
        CalculatedTaxDTO finalValueTax = taxServices.calculatedTax(requestForCalculatedTaxDTO);
        return ResponseEntity.ok(finalValueTax);
    }

    @GetMapping("/tipos")
    public ResponseEntity<List<ResponseTaxDTO>> listAllTaxes(){
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
