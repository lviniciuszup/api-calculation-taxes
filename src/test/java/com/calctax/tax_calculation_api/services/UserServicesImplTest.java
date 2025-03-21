package com.calctax.tax_calculation_api.services;

import com.calctax.tax_calculation_api.dtos.LoginUserDTO;
import com.calctax.tax_calculation_api.dtos.RegisterUserDTO;
import com.calctax.tax_calculation_api.dtos.ResponseUserDTO;
import com.calctax.tax_calculation_api.dtos.RoleName;
import com.calctax.tax_calculation_api.infra.JwtUtil;
import com.calctax.tax_calculation_api.models.Role;
import com.calctax.tax_calculation_api.models.User;
import com.calctax.tax_calculation_api.repositories.RoleRepository;
import com.calctax.tax_calculation_api.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class UserServicesImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @InjectMocks
    private UserServicesImpl userServices;

    @Mock
    private JwtUtil jwtUtil;
    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Mock
    private User user;

    @BeforeEach
    public void setUp(){
        user = new User();
        user.setUsername("userTest");
        user.setPassword(new BCryptPasswordEncoder().encode("testPassword"));

        Role roleUser = new Role();
        roleUser.setId(1L);
        roleUser.setName("ADMIN");
        user.setRoles(Set.of(roleUser));

    }

    @Test
    public void registerUserTest() {

        when(roleRepository.findByName(anyString())).thenReturn(Optional.of(new Role(1L, "ADMIN")));

        when(userRepository.save(any(User.class))).thenReturn(user);

        RegisterUserDTO registerUserDTO = new RegisterUserDTO();
        registerUserDTO.setUsername("userTest");
        registerUserDTO.setPassword("testPassword");
        registerUserDTO.setRoles(Set.of("ADMIN"));

        ResponseUserDTO registeredUser = userServices.registerUser(registerUserDTO);

        assertNotNull(registeredUser);
        assertEquals("userTest", registeredUser.getUsername());

        verify(userRepository, times(1)).save(any(User.class));
    }
/*
    @Test
    void loadUserByUsernameTest() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));

        UserDetails userDetails = userServices.loadUserByUsername("userTest");

        assertNotNull(userDetails, "Usuario nao encontrado");
        assertEquals("userTest", userDetails.getUsername());
        assertTrue(new BCryptPasswordEncoder().matches("testPassword", userDetails.getPassword()));
        assertEquals(Collections.singleton(new SimpleGrantedAuthority("ADMIN")), userDetails.getAuthorities());
    }
*/
    @Test
    void loginUser() {
        when(bCryptPasswordEncoder.matches(anyString(), anyString())).thenReturn(true);
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
        List<String> roles = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toList());

        when(jwtUtil.createToken(eq(roles), anyString())).thenReturn("Token");

        LoginUserDTO loginUserDTO = new LoginUserDTO();
        loginUserDTO.setUsername("userTest");
        loginUserDTO.setPassword("testPassword");

        String token = userServices.loginUser(loginUserDTO);

        assertEquals("Token", token);
        verify(userRepository, times(1)).findByUsername("userTest");
        verify(jwtUtil, times(1)).createToken(roles, "userTest"); //
    }
}