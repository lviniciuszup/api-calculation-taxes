package com.calctax.tax_calculation_api.services;

import com.calctax.tax_calculation_api.dtos.LoginUserDTO;
import com.calctax.tax_calculation_api.dtos.RegisterUserDTO;
import com.calctax.tax_calculation_api.dtos.ResponseUserDTO;

public interface UserServices {
    ResponseUserDTO registerUser(RegisterUserDTO registerUserDTO);
    String loginUser(LoginUserDTO loginUserDTO);
}
