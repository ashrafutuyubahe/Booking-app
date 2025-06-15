package com.hotel_mngs.TUYUBAHE_Ashrafu.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hotel_mngs.TUYUBAHE_Ashrafu.Dto.LoginDto;
import com.hotel_mngs.TUYUBAHE_Ashrafu.Dto.LoginResponseDto;
import com.hotel_mngs.TUYUBAHE_Ashrafu.Dto.RegisterDto;
import com.hotel_mngs.TUYUBAHE_Ashrafu.Models.User;
import com.hotel_mngs.TUYUBAHE_Ashrafu.Repository.UserRepository;
import com.hotel_mngs.TUYUBAHE_Ashrafu.Service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final MailService mailService;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JwtService jwtService,
            MailService mailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.mailService = mailService;
    }

    @Override
    public String registerUser(RegisterDto registerDto) {
        if (userRepository.findByUserEmail(registerDto.getUserEmail()).isPresent()) {
            return "Email already exists";
        }

        String hashedPassword = passwordEncoder.encode(registerDto.getUserPassword());

        User user = new User();
        user.setUserName(registerDto.getUserName());
        user.setUserEmail(registerDto.getUserEmail());
        user.setUserPassword(hashedPassword);

        userRepository.save(user);

        // Send welcome email after registration
        String subject = "Welcome to Hotel Booking App!";
        String body = "Dear " + user.getUserName()
                + ",\n\nThank you for registering at our hotel booking platform.\n\nBest regards,\nHotel Booking Team";
        mailService.sendEmail(user.getUserEmail(), subject, body);

        return "User registered successfully";
    }

    @Override
    public LoginResponseDto loginUser(LoginDto loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getUserName(),
                            loginDto.getUserPassword()));

            if (authentication.isAuthenticated()) {
                User user = userRepository.findByUserName(loginDto.getUserName())
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));

                String token = jwtService.generateToken(loginDto.getUserName());

                return new LoginResponseDto(token, user.getUserName(), user.getUserEmail(), "Login successful",
                        user.getUserId());
            }
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid username or password");
        } catch (UsernameNotFoundException e) {
            throw new UsernameNotFoundException("User not found");
        }
        throw new RuntimeException("Login failed");
    }

    @Override
    public String logoutrUser() {
        return "about to log out";
    }
}
