package com.calctax.tax_calculation_api.dtos;

import lombok.Data;

import java.util.Set;

@Data
public class RegisterUserDTO {
    private Long id;
    private String username;
    private String password;
    private Set<Roles> roles;
}
