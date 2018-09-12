package com.revature.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;
@Component
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	CustomAuthenticationProvider authprovider;
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		
	  auth.authenticationProvider(authprovider);
	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() //disabled for testing
        	.authorizeRequests().anyRequest().fullyAuthenticated()
            .and()
            .logout().deleteCookies("rememberme").logoutUrl("/BookAuction/logout").logoutSuccessUrl("/BookAuction/Login")
            .and()
            .httpBasic();
    }
}