package com.kusob.config.JwtConfig;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Created by seungki on 2017-07-13.
 */
public class JwtMember implements UserDetails {

    private final String loginId;
    private final String username;
    private final String password;

    private final Collection<? extends GrantedAuthority> authorities;
    private final boolean enabled;
    public JwtMember(
            String loginId,
            String username,
            String password,
            Collection<? extends GrantedAuthority> authorities
    ) {
        this.loginId = loginId;
        this.username = username;
        this.password=password;
        this.authorities = authorities;
        this.enabled = true;
    }
    @Override
    public String getUsername() {
        return username;
    }

    public String getLoginId(){return loginId;}


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
