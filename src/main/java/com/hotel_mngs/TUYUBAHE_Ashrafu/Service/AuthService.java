package com.hotel_mngs.TUYUBAHE_Ashrafu.Service;

import com.hotel_mngs.TUYUBAHE_Ashrafu.Dto.LoginDto;
import com.hotel_mngs.TUYUBAHE_Ashrafu.Dto.LoginResponseDto;
import com.hotel_mngs.TUYUBAHE_Ashrafu.Dto.RegisterDto;

public interface AuthService {
    String registerUser(RegisterDto registerDto);
    LoginResponseDto loginUser(LoginDto loginDto);
    String logoutrUser();
   
}
