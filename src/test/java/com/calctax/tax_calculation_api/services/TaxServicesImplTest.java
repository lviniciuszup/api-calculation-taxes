package com.calctax.tax_calculation_api.services;

import com.calctax.tax_calculation_api.dtos.CalculatedTaxDTO;
import com.calctax.tax_calculation_api.dtos.RegisterTaxDTO;
import com.calctax.tax_calculation_api.dtos.RequestForCalculatedTaxDTO;
import com.calctax.tax_calculation_api.dtos.ResponseTaxDTO;
import com.calctax.tax_calculation_api.models.Tax;
import com.calctax.tax_calculation_api.repositories.TaxRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaxServicesImplTest {
    @Mock
    private TaxRepository taxRepository;

    @InjectMocks
    private TaxServicesImpl taxServices;

    private Tax tax;
    private RegisterTaxDTO registerTaxDTO;
    private ResponseTaxDTO responseTaxDTO;
    private CalculatedTaxDTO calculatedTaxDTO;



    @Test
    void registerTax() {
        registerTaxDTO = new RegisterTaxDTO("IR", "Imposto sobre renda", BigDecimal.valueOf(18));

        tax = new Tax (registerTaxDTO.getName(), registerTaxDTO.getDescription(), registerTaxDTO.getAliquot());
        tax.setTaxId(1L);

        responseTaxDTO = new ResponseTaxDTO(tax.getTaxId(), tax.getTaxName(), tax.getDescription(), tax.getAliquot());

        when(taxRepository.existsByTaxName(registerTaxDTO.getName())).thenReturn(false);
        when(taxRepository.save(any(Tax.class))).thenReturn(tax);

        ResponseTaxDTO result = taxServices.registerTax(registerTaxDTO);

        // Assert
        assertNotNull(result);
        assertEquals(responseTaxDTO.getTaxId(), result.getTaxId());
        assertEquals(responseTaxDTO.getName(), result.getName());
        assertEquals(responseTaxDTO.getDescription(), result.getDescription());
        assertEquals(responseTaxDTO.getAliquot(), result.getAliquot());

    }

    @Test
    void getTaxById() {
        Long taxId = 1L;
        tax = new Tax("ICMS", "Imposto sobre circulação de mercadorias", BigDecimal.valueOf(15));
        tax.setTaxId(taxId);

        when(taxRepository.findTaxByTaxId(taxId)).thenReturn(Optional.of(tax));

        ResponseTaxDTO result = taxServices.getTaxById(taxId);

        assertNotNull(result);
        assertEquals(tax.getTaxId(), result.getTaxId());
        assertEquals(tax.getTaxName(), result.getName());
        assertEquals(tax.getDescription(), result.getDescription());
        assertEquals(tax.getAliquot(), result.getAliquot());
    }

    @Test
    void calculatedTax() {
        Long taxId = 1L;
        BigDecimal baseValue = BigDecimal.valueOf(1000);
        RequestForCalculatedTaxDTO request = new RequestForCalculatedTaxDTO(taxId, baseValue);

        Tax tax = new Tax("ICMS", "Imposto sobre Circulação de Mercadorias", BigDecimal.valueOf(18));
        tax.setTaxId(taxId);

        when(taxRepository.findTaxByTaxId(taxId)).thenReturn(Optional.of(tax));

        CalculatedTaxDTO result = taxServices.calculatedTax(request);

        assertNotNull(result);
        assertEquals("ICMS", result.getName());
        assertEquals(baseValue, result.getBaseValue());
        assertEquals(BigDecimal.valueOf(18), result.getAliquot());
        assertEquals(BigDecimal.valueOf(180), result.getCalculatedValue()); // 1000 * 18 / 100
    }

    @Test
    void getAllTaxes() {
        List<Tax> taxes = List.of(
                new Tax("ICMS", "Imposto sobre Circulação", BigDecimal.valueOf(18)),
                new Tax("IPI", "Imposto sobre Produtos", BigDecimal.valueOf(10))
        );

        when(taxRepository.findAll()).thenReturn(taxes);

        List<ResponseTaxDTO> result = taxServices.getAllTaxes();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("ICMS", result.get(0).getName());
        assertEquals("IPI", result.get(1).getName());
    }

    @Test
    void deleteTaxById() {
        long taxId = 1L;
        tax = new Tax("ICMS", "Imposto sobre Circulação", BigDecimal.valueOf(12));
        tax.setTaxId(taxId);

        when(taxRepository.findTaxByTaxId(taxId)).thenReturn(Optional.of(tax));

        assertDoesNotThrow(() -> taxServices.deleteTaxById(taxId));
        verify(taxRepository).delete(tax);
    }
}