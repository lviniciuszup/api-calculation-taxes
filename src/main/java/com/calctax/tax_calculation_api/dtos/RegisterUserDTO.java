package com.calctax.tax_calculation_api.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


public class RegisterUserDTO {
    @NotEmpty(message = "O usuário não pode ser vázio")
    @Size(min = 3, max = 20, message = "Por favor, o nome de usuário precisa ter entre 3 e 20 caracteres" )
    private String username;
    @NotEmpty(message = "A senha não pode ser vázia")
    @Size(min = 6, message = "Por favor, a senha precisa ter no mínimo 6 caracteres" )
    private String password;
    private Set<String> roles;


    public RegisterUserDTO(String username, String password, Set<String>roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;

    }

    public RegisterUserDTO() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
