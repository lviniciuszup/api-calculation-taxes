package com.calctax.tax_calculation_api.dtos;

import com.calctax.tax_calculation_api.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class ResponseUserDTO {
    private Long id;
    private String username;
    private Set<Role> roles;
}
