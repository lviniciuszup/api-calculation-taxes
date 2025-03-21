package com.calctax.tax_calculation_api.services;

import com.calctax.tax_calculation_api.dtos.*;
import com.calctax.tax_calculation_api.exceptions.DuplicateException;
import com.calctax.tax_calculation_api.exceptions.NotFoundException;
import com.calctax.tax_calculation_api.models.Tax;
import com.calctax.tax_calculation_api.repositories.TaxRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TaxServicesImpl implements TaxServices {
    @Autowired
    private TaxRepository taxRepository;

    public ResponseTaxDTO registerTax(RegisterTaxDTO registerTaxDTO){
        String taxName = registerTaxDTO.getName();
        if (taxRepository.existsByTaxName(taxName)){
            throw new DuplicateException("Essa taxa já existe: " +taxName);
        }

        Tax tax = new Tax(taxName, registerTaxDTO.getDescription(), registerTaxDTO.getAliquot());

        Tax registeredTax = taxRepository.save(tax);

        return new ResponseTaxDTO(
                registeredTax.getTaxId(),
                registeredTax.getTaxName(),
                registeredTax.getDescription(),
                registeredTax.getAliquot()
        );
    }
    public ResponseTaxDTO getTaxById(Long taxId){
        Tax taxFoundById = taxRepository.findTaxByTaxId(taxId)
                .orElseThrow(() -> new NotFoundException("O imposto com o id "+taxId+" não foi encontrado."));

        return new ResponseTaxDTO(
                taxFoundById.getTaxId(),
                taxFoundById.getTaxName(),
                taxFoundById.getDescription(),
                taxFoundById.getAliquot()
        );
    }

    public CalculatedTaxDTO calculatedTax(RequestForCalculatedTaxDTO requestForCalculatedTaxDTO){
        Long taxId = requestForCalculatedTaxDTO.getTaxId();
        Optional<Tax> taxOpt = taxRepository.findTaxByTaxId(taxId);
        if (!taxOpt.isPresent()){
            throw new NotFoundException("Imposto não encontrado para o ID "+taxId+" fornecido!");
        }
        Tax tax = taxOpt.get();

        BigDecimal baseValue = requestForCalculatedTaxDTO.getBaseValue();
        BigDecimal aliquot = tax.getAliquot();
        BigDecimal calculatedValue = baseValue.multiply(aliquot).divide(BigDecimal.valueOf(100));

        CalculatedTaxDTO finalValue = new CalculatedTaxDTO();
        finalValue.setName(tax.getTaxName());
        finalValue.setBaseValue(baseValue);
        finalValue.setAliquot(aliquot);
        finalValue.setCalculatedValue(calculatedValue);

        return finalValue;
    }

    public List<ResponseTaxDTO> getAllTaxes(){
        return taxRepository.findAll().stream()
                .map(tax -> new ResponseTaxDTO(
                        tax.getTaxId(),
                        tax.getTaxName(),
                        tax.getDescription(),
                        tax.getAliquot()
                )).collect(Collectors.toList());
    }

    public void deleteTaxById(Long taxId){
          Tax existingTax = taxRepository.findTaxByTaxId(taxId)
                  .orElseThrow(() -> new NotFoundException("Não existe imposto com esse id!"));

          taxRepository.delete(existingTax);
    }
    }