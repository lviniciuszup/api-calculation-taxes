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
public class UserServicesImpl implements UserServices {
    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private JwtUtil jwtUtil;

    public UserServicesImpl(RoleRepository roleRepository, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, JwtUtil jwtUtil) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public ResponseUserDTO registerUser(RegisterUserDTO registerUserDTO) {
        String username = registerUserDTO.getUsername();
        if (userRepository.existsByUsername(username)){
            throw new DuplicateException("Já existe um usuário com este nome" +username);
        }

        String encodedPassword = bCryptPasswordEncoder.encode(registerUserDTO.getPassword());

        Set<Role> roles = registerUserDTO.getRoles().stream()
                .map(roleNameString -> {
                    String roleNameWithPrefix = roleNameString.toUpperCase().replace("ROLE_", "");

                    Role role = new Role();
                    role.setName(roleNameWithPrefix);
                    if (!roleRepository.existsByName(roleNameWithPrefix)) {
                        roleRepository.save(role);
                   }
                    return roleRepository.findByName(roleNameWithPrefix)
                            .orElseThrow(() -> new NotFoundException("Esta role não encontrada: " + roleNameWithPrefix));
                })
                .collect(Collectors.toSet());

        User user = new User(username, encodedPassword, roles);
        User registeredUser = userRepository.save(user);

        return new ResponseUserDTO(
                registeredUser.getId(),
                registeredUser.getUsername(),
                registeredUser.getRoles()
        );
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
                    .map(role -> role.getName())
                    .collect(Collectors.toList());

            return jwtUtil.createToken(roles, user.getUsername());
        }
    }