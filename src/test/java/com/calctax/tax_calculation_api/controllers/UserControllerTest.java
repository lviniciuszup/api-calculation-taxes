package com.calctax.tax_calculation_api.controllers;

import com.calctax.tax_calculation_api.dtos.LoginUserDTO;
import com.calctax.tax_calculation_api.dtos.RegisterUserDTO;
import com.calctax.tax_calculation_api.dtos.ResponseUserDTO;
import com.calctax.tax_calculation_api.models.Role;
import com.calctax.tax_calculation_api.services.UserServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    private MockMvc mockMvc;
    @Mock
    private UserServices userServices;
    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void newUser() throws Exception {
        Role role = new Role(1L, "ADMIN");
        Set<Role> roles = Set.of(role);
        ResponseUserDTO responseUserDTO = new ResponseUserDTO(1L, "userTest", roles);

        when(userServices.registerUser(any(RegisterUserDTO.class))).thenReturn(responseUserDTO);

        String requestRegisterUserTest = """
                {
                      "username": "userTest",
                      "password": "123456",
                      "roles": ["ADMIN"]
                }
                """;

        mockMvc.perform(post("/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestRegisterUserTest))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("userTest"))
                .andExpect(jsonPath("$.roles[0].name").value("ADMIN"));
    }


    @Test
    void loginUser() throws Exception {
            String token = "token";
        when(userServices.loginUser(any(LoginUserDTO.class))).thenReturn(token);

        String requestLoginUserTest = """
              {
                 "username": "userTest",
                 "password": "123456"
               }
              """;

            mockMvc.perform(post("/user/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestLoginUserTest))
                    .andExpect(status().isOk())
                    .andExpect(content().string(token));
        }

    }