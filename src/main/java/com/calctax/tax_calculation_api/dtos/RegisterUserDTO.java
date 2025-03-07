package com.calctax.tax_calculation_api.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class UserDTO {
    private String name;
    private String username;
    private String password;
    private Set<Roles> roles;
}
