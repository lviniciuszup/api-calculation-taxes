package com.calctax.tax_calculation_api.services;

import com.calctax.tax_calculation_api.dtos.LoginUserDTO;
import com.calctax.tax_calculation_api.dtos.RegisterUserDTO;
import com.calctax.tax_calculation_api.dtos.ResponseUserDTO;
import com.calctax.tax_calculation_api.exceptions.DuplicateException;
import com.calctax.tax_calculation_api.exceptions.InvalidException;
import com.calctax.tax_calculation_api.exceptions.NotFoundException;
import com.calctax.tax_calculation_api.infra.security.JwtUtil;
import com.calctax.tax_calculation_api.models.Role;
import com.calctax.tax_calculation_api.models.User;
import com.calctax.tax_calculation_api.repositories.RoleRepository;
import com.calctax.tax_calculation_api.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
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
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtUtil jwtUtil;

    private final Map<String, String> userToken = new HashMap<>();

    public ResponseUserDTO registerUser(RegisterUserDTO registerUserDTO) {
        String username = registerUserDTO.getUsername();
        if (userRepository.existsByUsername(username)){
            throw new DuplicateException("Já existe um usuário com este nome" + username);
        }

        String encodedPassword = bCryptPasswordEncoder.encode(registerUserDTO.getPassword());

        Set<Role> roles = registerUserDTO.getRoles().stream()
                .map(roleName -> roleRepository.findByName(roleName)
                        .orElseThrow(() -> new NotFoundException("Está role não existe " +roleName))).collect(Collectors.toSet());

        User user = new User(username, encodedPassword, roles);
        User registeredUser = userRepository.save(user);

        String token = jwtUtil.generateToken(user.getRoles().stream()
                .map(role -> role.getName().toString()).collect(Collectors.toList()), user.getUsername());

        userToken.put(username, token);

        return new ResponseUserDTO(
                registeredUser.getId(),
                registeredUser.getUsername(),
                registeredUser.getRoles()
        );
    }
    public String getUserToken(String username){
        return userToken.get(username);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado " +username));

        String token = userToken.get(username);
        if (token == null || !jwtUtil.validateToken(token)) {
            throw new BadCredentialsException("Token inválido ou expirado");
        }
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
        }

        public String loginUser(LoginUserDTO loginUserDTO){
            Optional<User> optionalUser = userRepository.findByUsername(loginUserDTO.getUsername());
            if (!optionalUser.isPresent()){
                throw new NotFoundException("Usuáaio não encontrado");
            }
            User user = optionalUser.get();

            if(!bCryptPasswordEncoder.matches(loginUserDTO.getPassword(), user.getPassword())){
                throw new InvalidException("Senha inválida.");
            }

            List<String> roles = user.getRoles().stream()
                    .map(role -> role.getName().name())
                    .collect(Collectors.toList());

            return jwtUtil.generateToken(roles, user.getUsername());
        }
    }