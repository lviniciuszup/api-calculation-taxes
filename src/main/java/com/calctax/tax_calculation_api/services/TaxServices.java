package com.calctax.tax_calculation_api.services;

import com.calctax.tax_calculation_api.dtos.LoginUserDTO;
import com.calctax.tax_calculation_api.dtos.RegisterUserDTO;
import com.calctax.tax_calculation_api.dtos.ResponseUserDTO;
import com.calctax.tax_calculation_api.models.User;
import org.apache.coyote.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface UserServices {
    ResponseUserDTO registerUser(RegisterUserDTO registerUserDTO);
    String loginUser(LoginUserDTO loginUserDTO);
}
