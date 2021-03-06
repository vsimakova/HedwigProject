package com.hedwigbookclub.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/admin/**").hasAnyRole("ROLE_ADMIN")
				.antMatchers("/cart").hasAuthority("ROLE_USER")
				.antMatchers("/dashboard", "/shopping_books", "/book_info").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
				.antMatchers("/register_book","/registration_book", "/success_register_book", "/users", "/user").hasAuthority("ROLE_ADMIN")
				.antMatchers("/css/*.css", "/js/*.js", "/images/**", "/favicon_hedwig/**", "/php/*.php").permitAll()
				.antMatchers("/register","/registration", "/success").permitAll()
				.anyRequest().hasAnyRole("USER").and()
			.formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/login")
				.defaultSuccessUrl("/dashboard", true)
				.permitAll()
				.and()
				.exceptionHandling().accessDeniedPage("/403");
	}
}