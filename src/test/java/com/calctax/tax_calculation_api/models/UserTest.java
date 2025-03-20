package com.calctax.tax_calculation_api.models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


class UserTest {

    @Test
    void userTest() {
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(1L, "ADMIN"));
        User user = new User(1L, "userTest", "testPassword", roles);

        assertEquals(1L, user.getId());
        assertEquals("userTest", user.getUsername());
        assertEquals("testPassword", user.getPassword());
        assertEquals(1, user.getRoles().size());

    }

}