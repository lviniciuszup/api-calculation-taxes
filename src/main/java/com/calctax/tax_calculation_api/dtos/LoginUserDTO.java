package com.calctax.tax_calculation_api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class LoginUserDTO {
    private String username;
    private String password;

    public LoginUserDTO(String password, String username) {
        this.password = password;
        this.username = username;
    }

    public LoginUserDTO() {}

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
}
