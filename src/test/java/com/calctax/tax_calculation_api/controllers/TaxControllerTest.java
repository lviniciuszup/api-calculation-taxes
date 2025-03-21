package com.calctax.tax_calculation_api.controllers;

import com.calctax.tax_calculation_api.dtos.CalculatedTaxDTO;
import com.calctax.tax_calculation_api.dtos.RegisterTaxDTO;
import com.calctax.tax_calculation_api.dtos.RequestForCalculatedTaxDTO;
import com.calctax.tax_calculation_api.dtos.ResponseTaxDTO;
import com.calctax.tax_calculation_api.services.TaxServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
class TaxControllerTest {
    private MockMvc mockMvc;
    @Mock
    private TaxServices taxServices;
    @InjectMocks
    private TaxController taxController;
    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(taxController).build();
    }

    @Test
    void createTax() throws Exception {
        ResponseTaxDTO responseTaxDTO = new ResponseTaxDTO(1L, "IR", "Imposto sobre renda", BigDecimal.valueOf(15));

        doReturn(responseTaxDTO).when(taxServices).registerTax(any(RegisterTaxDTO.class));

        String requestRegisterTaxTest = """
                {
                      "name": "IR",
                      "description": "Imposto sobre renda",
                      "aliquot": "15"
                }
                """;

        mockMvc.perform(post("/taxes/tipos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestRegisterTaxTest))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.taxId").value(1))
                .andExpect(jsonPath("$.name").value("IR"))
                .andExpect(jsonPath("$.description").value("Imposto sobre renda"))
                .andExpect(jsonPath("$.aliquot").value("15"));

    }

    @Test
    void calculateTax() throws Exception {

        CalculatedTaxDTO calculatedTaxDTO = new CalculatedTaxDTO("IR", BigDecimal.valueOf(1000), BigDecimal.valueOf(15), BigDecimal.valueOf(150));

        when(taxServices.calculatedTax(any(RequestForCalculatedTaxDTO.class))).thenReturn(calculatedTaxDTO);

        String newRequestForCalculatedTax = """
                {
                       "taxId": 1,
                      "baseValue": "1000"
                }
                """;

        mockMvc.perform(post("/taxes/calculo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newRequestForCalculatedTax))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("IR"))
                .andExpect(jsonPath("$.baseValue").value(1000))
                .andExpect(jsonPath("$.aliquot").value(15))
                .andExpect(jsonPath("$.calculatedValue").value(150));

    }

    @Test
    void listAllTaxes() throws Exception {
        ResponseTaxDTO taxCreate1 = new ResponseTaxDTO(1L, "IR", "Imposto de Renda", BigDecimal.valueOf(15));
        ResponseTaxDTO taxCreate2 = new ResponseTaxDTO(2L, "ICMS", "Imposto sobre Circulação de Mercadorias", BigDecimal.valueOf(18));
        List<ResponseTaxDTO> allTaxes = List.of(taxCreate1, taxCreate2);

        when(taxServices.getAllTaxes()).thenReturn(allTaxes);

        mockMvc.perform(get("/taxes/tipos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].taxId").value(1))
                .andExpect(jsonPath("$[0].name").value("IR"))
                .andExpect(jsonPath("$[0].description").value("Imposto de Renda"))
                .andExpect(jsonPath("$[1].taxId").value(2))
                .andExpect(jsonPath("$[1].name").value("ICMS"))
                .andExpect(jsonPath("$[1].description").value("Imposto sobre Circulação de Mercadorias"));
    }

    @Test
    void listTaxesById() throws Exception {
        ResponseTaxDTO tax = new ResponseTaxDTO(1L, "IR", "Imposto de Renda", BigDecimal.valueOf(15));

        when(taxServices.getTaxById(1L)).thenReturn(tax);

        mockMvc.perform(get("/taxes/tipos/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.taxId").value(1))
                .andExpect(jsonPath("$.name").value("IR"))
                .andExpect(jsonPath("$.description").value("Imposto de Renda"));
    }


    @Test
    void deleteTaxById() throws Exception {
        long taxId = 1L;
        mockMvc.perform(delete("/taxes/tipos/{id}", taxId))
                .andExpect(status().isNoContent());
    }
}