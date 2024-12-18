package com.nt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	public void configure(AuthenticationManagerBuilder http) throws Exception {
     //for Authentication info provider configration
		http.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("raja").password("$2a$10$48Zc8RPueoxN95m1i2OpqunB1CCjh1MD0E9LypR0VVXWjiAKVSspW").roles("CUSTOMER");   //"{noop}rani"
		http.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("rajesh").password("$2a$10$1SBo6Qtc/XsF/pUAjseMjOAe2iDMItwgA9zXivTnVj7vWV2YOxtnC").roles("MANAGER");//"{noop}hyd"
		http.inMemoryAuthentication().withUser("mahesh").password("{noop}delhi").roles("MANAGER","CUSTOMER");
		http.inMemoryAuthentication().withUser("suresh").password("{noop}vizag").roles("VISITOR");
		
		
		
		
	}

	public void configure(HttpSecurity http) throws Exception {
		
		//config Authentication + Authorization
		http.authorizeHttpRequests().antMatchers("/").permitAll()
		.antMatchers("/offers").authenticated()
		.antMatchers("/balance").hasAnyRole("MANAGER","CUSTOMER")
		.antMatchers("/loanApprove").hasRole("MANAGER")
	//	.anyRequest().authenticated().and().httpBasic()
		.anyRequest().authenticated().and().formLogin()
		.and().exceptionHandling().accessDeniedPage("/denied").and().logout()
		.and().sessionManagement().maximumSessions(2).maxSessionsPreventsLogin(true);
		
		

	}
}
