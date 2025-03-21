package com.calctax.tax_calculation_api.dtos;

import com.calctax.tax_calculation_api.models.Role;
import com.calctax.tax_calculation_api.models.Tax;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ResponseUserDTOTest {

    @Test
    void testResponseUserDTO() {
        Role role = new Role(1L, "ADMIN");
        Set<Role> roles = Set.of(role);

        ResponseUserDTO dto = new ResponseUserDTO(1L, "userTest", roles);
        assertEquals(1L, dto.getId());
        assertEquals("userTest", dto.getUsername());
        assertEquals(roles, dto.getRoles());

    }

}