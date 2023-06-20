package com.cy.douyin.model.security;

import com.cy.douyin.model.domain.UserCore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 这里面封装了对数据库权限字段的处理,根据数字给予对应的角色
 */
public class CustomUserDetails implements UserDetails {

    private final UserCore userCore;

    private final List<GrantedAuthority> authorities;

    public CustomUserDetails(UserCore userCore) {
        this.userCore = userCore;
        Integer userRole = userCore.getUser_role();
        authorities =new ArrayList<>();
        if (userRole==0) authorities.add(new SimpleGrantedAuthority("normal"));
        else if (userRole==1) authorities.add(new SimpleGrantedAuthority("admin"));
    }

    public UserCore getUser() {
        return userCore;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return userCore.getPassword();
    }

    @Override
    public String getUsername() {
        return userCore.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return userCore.getUser_status()==0;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return userCore.getIs_delete()==0;
    }
}
