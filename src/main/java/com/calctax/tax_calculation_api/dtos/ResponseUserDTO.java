package com.calctax.tax_calculation_api.dtos;

import com.calctax.tax_calculation_api.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data

public class ResponseUserDTO {
    private Long id;
    private String username;
    private Set<Role> roles;

    public ResponseUserDTO(Long id, String username, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
