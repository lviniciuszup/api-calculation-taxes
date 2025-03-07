package com.calctax.tax_calculation_api.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class RegisterUserDTO {
    @NotEmpty(message = "O usuário não pode ser vázio")
    @Size(min = 3, max = 20, message = "Por favor, o nome de usuário precisa ter entre 3 e 20 caracteres" )
    private String username;
    @NotEmpty(message = "A senha não pode ser vázia")
    @Size(min = 6, message = "Por favor, a senha precisa ter no mínimo 6 caracteres" )
    private String password;
    private Set<Roles> roles;
}
