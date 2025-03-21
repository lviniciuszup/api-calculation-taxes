package com.calctax.tax_calculation_api.dtos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginUserDTOTest {

    @Test
    void testLoginUserDTO() {

        LoginUserDTO dto = new LoginUserDTO("123456", "userTest");
        assertEquals("userTest", dto.getUsername());
        assertEquals("123456", dto.getPassword());
    }

}