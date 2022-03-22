package com.hedwigbookclub.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.hedwigbookclub.domain.User;

public class CustomSecurityUser extends User implements UserDetails {
	private static final long serialVersionUID = -4901234625858788322L;

	public CustomSecurityUser() {}
	
	public CustomSecurityUser(User user) {
		this.setAuthorities(user.getAuthorities());
		this.setId(user.getId());
		this.setPassword(user.getPassword());
		this.setUsername(user.getUsername());
	}

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
	public boolean isEnabled() {
		return true;
	}
	
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		Set<Authorities> auth = user.getAuthoritiess();
//		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//		
//		for (Authorities authority : auth) {
//			authorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
//		}
//		return authorities;
//	}

}
