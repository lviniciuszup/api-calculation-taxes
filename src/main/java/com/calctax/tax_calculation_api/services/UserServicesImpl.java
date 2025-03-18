package com.calctax.tax_calculation_api.services;

import com.calctax.tax_calculation_api.dtos.LoginUserDTO;
import com.calctax.tax_calculation_api.dtos.RegisterUserDTO;
import com.calctax.tax_calculation_api.dtos.ResponseUserDTO;
import com.calctax.tax_calculation_api.dtos.RoleName;
import com.calctax.tax_calculation_api.exceptions.DuplicateException;
import com.calctax.tax_calculation_api.exceptions.InvalidException;
import com.calctax.tax_calculation_api.exceptions.NotFoundException;
import com.calctax.tax_calculation_api.infra.JwtUtil;
import com.calctax.tax_calculation_api.models.Role;
import com.calctax.tax_calculation_api.models.User;
import com.calctax.tax_calculation_api.repositories.RoleRepository;
import com.calctax.tax_calculation_api.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServicesImpl implements UserServices, UserDetailsService {
    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private JwtUtil jwtUtil;

    public ResponseUserDTO registerUser(RegisterUserDTO registerUserDTO) {
        String username = registerUserDTO.getUsername();
        if (userRepository.existsByUsername(username)){
            throw new DuplicateException("Já existe um usuário com este nome" +username);
        }

        String encodedPassword = bCryptPasswordEncoder.encode(registerUserDTO.getPassword());

        Set<Role> roles = registerUserDTO.getRoles().stream()
                .map(roleNameString -> {
                    RoleName roleName;
                    try {
                        roleName = RoleName.valueOf(roleNameString.toUpperCase());
                    } catch (IllegalArgumentException e) {
                        throw new NotFoundException("Esta role não existe: " + roleNameString);
                    }
                    return roleRepository.findByName(roleName)
                            .orElseThrow(() -> new NotFoundException("Esta role não existe: " + roleNameString));
                })
                .collect(Collectors.toSet());

        User user = new User(null, username, encodedPassword, roles);
        User registeredUser = userRepository.save(user);

        return new ResponseUserDTO(
                registeredUser.getId(),
                registeredUser.getUsername(),
                registeredUser.getRoles()
        );
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado " +username));

        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
        }

        public String loginUser(LoginUserDTO loginUserDTO){
            Optional<User> existingUser = userRepository.findByUsername(loginUserDTO.getUsername());
            if (!existingUser.isPresent()){
                throw new NotFoundException("Usuário não encontrado");
            }
            User user = existingUser.get();

            if(!bCryptPasswordEncoder.matches(loginUserDTO.getPassword(), user.getPassword())){
                throw new InvalidException("Senha inválida.");
            }
            List<String> roles = user.getRoles().stream()
                    .map(role -> role.getName().name())
                    .collect(Collectors.toList());

            return jwtUtil.createToken(roles, user.getUsername());
        }
    }