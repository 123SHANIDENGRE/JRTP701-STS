package com.nt.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Autowired
	private DataSource  ds;
	
	public void configure(AuthenticationManagerBuilder http) throws Exception {
     //for Authentication info provider configration
		 http.jdbcAuthentication().dataSource(ds).passwordEncoder(new BCryptPasswordEncoder())
		 .usersByUsernameQuery("SELECT  UNAME ,PWD,STATUS   FROM jrtp701db.users  where UNAME=?")   //for authentication
		.authoritiesByUsernameQuery("SELECT  UNAME ,ROLE FROM jrtp701db.user_roles where UNAME=? ");   //for authorization
 		
		
	}

	public void configure(HttpSecurity http) throws Exception {
		
		//config Authentication + Authorization
		http.authorizeHttpRequests().antMatchers("/").permitAll()
		.antMatchers("/offers").authenticated()
		.antMatchers("/balance").hasAnyAuthority("MANAGER","CUSTOMER")
		.antMatchers("/loanApprove").hasAuthority("MANAGER")
	//	.anyRequest().authenticated().and().httpBasic()
		.anyRequest().authenticated()
		.and().formLogin()
		.and().exceptionHandling().accessDeniedPage("/denied").and().logout()
		.and().sessionManagement().maximumSessions(2).maxSessionsPreventsLogin(true);
		
		

	}
}
