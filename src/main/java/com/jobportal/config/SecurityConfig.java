package com.jobportal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	 @Bean public SecurityFilterChain securityFilterChain(HttpSecurity http)throws
		Exception {
		  http.csrf(csrf -> csrf.disable())
          .authorizeHttpRequests(auth -> auth
              .requestMatchers("/login","/css/**","/js/**").permitAll()
              .requestMatchers("/userdetail").hasAnyRole("USER","ADMIN")
			  .requestMatchers("/user/**","/admin/**").authenticated().anyRequest().permitAll()
              
          )

          .formLogin(form -> form
              .loginPage("/login")
              .successHandler(authenticationSuccessHandler())
              .permitAll()
          )
			
			 .logout(logout -> logout.logoutUrl("/logout") 
					 .logoutSuccessUrl("/login?logout")
					 .invalidateHttpSession(true).permitAll() 
					 );
	  return http.build();
	  }
	 

	  @Bean public PasswordEncoder passwordEncoder() {
		  return new BCryptPasswordEncoder(); 
	  }
	  
	  @Bean
	    public AuthenticationSuccessHandler authenticationSuccessHandler() {
	        return (request, response, authentication) -> {

	            boolean isAdmin = authentication.getAuthorities().stream()
	                    .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

	            if (isAdmin) {
	                response.sendRedirect("/admin/admindashboard");
	            } else {
	                response.sendRedirect("/home");
	            }
	        };
	    }
}

