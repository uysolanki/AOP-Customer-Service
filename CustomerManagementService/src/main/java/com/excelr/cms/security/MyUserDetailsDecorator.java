package com.excelr.cms.security;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.excelr.cms.entity.Role;
import com.excelr.cms.entity.User;

public class MyUserDetailsDecorator implements UserDetails {
	
	public User user;
	
	public MyUserDetailsDecorator(User user)
	{
		this.user=user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() 
	{
		List<Role> roles = user.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
         
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRolename()));
        }
         
        return authorities;

	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		LocalDate accountExpiryDate=user.getAccountExpiryDate();  //10-June-2024
		LocalDate todaysDate=LocalDate.now();              //13-June-2024
		if(accountExpiryDate.isAfter(todaysDate))
			return true;
		else
			return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		int accountLockedStatus=user.getAccountLockedStatus();
		if(accountLockedStatus==1)
			return true;
		else
			return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		LocalDate credentialExpiryDate=user.getCredentialExpiryDate();  //10-June-2024
		LocalDate todaysDate=LocalDate.now();              //13-June-2024
		if(credentialExpiryDate.isAfter(todaysDate))
			return true;
		else
			return false;
	}

	@Override
	public boolean isEnabled() {
		int accountLEnabledStatus=user.getAccountEnabledStatus();
		if(accountLEnabledStatus==1)
			return true;
		else
			return false;
	}

}
