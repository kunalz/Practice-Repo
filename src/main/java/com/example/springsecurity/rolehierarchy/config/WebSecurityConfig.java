package com.example.springsecurity.rolehierarchy.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.inMemoryAuthentication()
			.withUser("admin").password("{noop}password").
			authorities("ROLE_ADMIN")
			.and()
			.withUser("manager").password("{noop}password").authorities("ROLE_MANAGER")
			.and()
			.withUser("user").password("{noop}password").authorities("ROLE_USER");
	}
	
	
	
	 
}
