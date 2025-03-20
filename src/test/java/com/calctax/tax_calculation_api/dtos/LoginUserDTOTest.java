package com.calctax.tax_calculation_api.dtos;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class LoginUserDTOTest {

    @Test
    void testLoginUserDTO() {

        LoginUserDTO dto = new LoginUserDTO("userTest", "123456");


        assertEquals("userTest", dto.getUsername());
        assertEquals("123456", dto.getPassword());
    }

}