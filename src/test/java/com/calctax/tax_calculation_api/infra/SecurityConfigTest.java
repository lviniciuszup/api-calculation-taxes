package com.calctax.tax_calculation_api.infra;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class SecurityConfigTest {
/*
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testPublicEndpoint() throws Exception {
        mockMvc.perform(get("/user/login"))
                .andExpect(status().isOk());
    }

    @Test
    public void testProtectedEndpointWithoutAuth() throws Exception {
        mockMvc.perform(get("/taxes/tipos"))
                .andExpect(status().isForbidden()); // Ou isUnauthorized(), dependendo da configuração
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testProtectedEndpointWithAuth() throws Exception {
        mockMvc.perform(get("/taxes/tipos"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testAdminEndpointWithUserRole() throws Exception {
        mockMvc.perform(post("/taxes/tipos"))
                .andExpect(status().isForbidden());
    }


    @Test
    @WithMockUser(roles = "ADMIN")
    public void testAdminEndpointWithAdminRole() throws Exception {
        mockMvc.perform(post("/taxes/tipos"))
                .andExpect(status().isOk());
    }
*/

}