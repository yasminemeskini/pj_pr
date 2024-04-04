package com.example.webdevboat.yacht.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.webdevboat.yacht.config.JwtAuthenticationFilter;
import com.example.webdevboat.yacht.services.UserService;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfigurer  {
	@Autowired
    private UserService userService;
	@Autowired
    private JwtAuthenticationFilter jwtAuthentificationFilter;
   
	 @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http.csrf().disable()
	        .cors() 
            .and()
	            .authorizeRequests(request ->
	                request
	                .antMatchers("/api/auth/login", "/h2-console/**", "/api/auth/signup", "/api/admin/boat", "/api/admin/boats", "/api/customer/boat").permitAll()
                    .antMatchers(HttpMethod.DELETE, "/api/admin/boat/{id}").permitAll()
                    .antMatchers(HttpMethod.GET, "/api/admin/boat/{id}").permitAll()
                    .antMatchers(HttpMethod.PUT, "/api/admin/boat/{id}").permitAll()
                    .antMatchers("/api/customer/boat/book").permitAll()
                    .antMatchers("/api/customer/boat/bookings/{userId}").permitAll() // Exiger une authentification pour cet endpoint
                    .antMatchers("/api/users/allUsers").permitAll()
                    .antMatchers("/api/admin/book").permitAll()
                    .antMatchers ("/api/admin/boat/bookings").permitAll()
                    .antMatchers ("/api/admin/boat/booking/{bookingId}/{status}").permitAll()

                    .antMatchers("/api/user/**").hasRole("USER")
                    .anyRequest().authenticated()
	            )
	            .sessionManagement()
	                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	        http.headers().frameOptions().disable();

	        return http.build();
	    }
	


@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}
@Bean
public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authprovider = new DaoAuthenticationProvider();
    authprovider.setUserDetailsService(userService.userDetailsService());
    authprovider.setPasswordEncoder(passwordEncoder());
    return authprovider;
}
@Bean
public AuthenticationManager authenticationManager(AuthenticationConfiguration config)throws Exception {
    return config.getAuthenticationManager();
}





}