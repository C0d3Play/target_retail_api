package com.myretail.api.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	      auth.inMemoryAuthentication()
	      		.withUser("admin").password("{noop}admin").roles("CUSTOMER", "ADMIN").and()
	      	    .withUser("customer").password("{noop}password").roles("CUSTOMER");
	 }
	
	 @Override
	 protected void configure(HttpSecurity http) throws Exception {
	      http.csrf().disable()
	         .authorizeRequests()
	         .antMatchers(HttpMethod.PUT).hasAnyRole("ADMIN")
	         .antMatchers("/**").hasAnyRole("CUSTOMER")
	         .anyRequest().authenticated()
	         .and().httpBasic();
	         //.and().formLogin().permitAll();
	 }
	   
}
