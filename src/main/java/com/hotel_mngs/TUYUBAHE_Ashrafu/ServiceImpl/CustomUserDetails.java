package com.hotel_mngs.TUYUBAHE_Ashrafu.ServiceImpl;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.hotel_mngs.TUYUBAHE_Ashrafu.Models.User;


public class CustomUserDetails implements UserDetails {

    private String name;
    private String password;
    private String role;

    public CustomUserDetails(User user) {

        this.name = user.getUserName();
        this.password = user.getUserPassword();
        this.role = user.getRole();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.name;
    }


    
}
