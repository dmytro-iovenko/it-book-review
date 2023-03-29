package com.example.review.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

import com.example.review.security.filter.AuthenticationFilter;
import com.example.review.security.filter.ExceptionHandlerFilter;
import com.example.review.security.filter.JWTAuthorizationFilter;
import com.example.review.security.manager.CustomAuthenticationManager;
import com.example.review.service.UserDetailsServiceImpl;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {
    private CustomAuthenticationManager customAuthenticationManager;
	
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(customAuthenticationManager);
        authenticationFilter.setFilterProcessesUrl("/api/auth");
        http
            .headers(headers -> headers.frameOptions().disable()).csrf(csrf -> csrf.disable())
            .authorizeRequests(requests -> requests
                .antMatchers("/h2/**").permitAll() // New Line: allows us to access the h2 console without the need to authenticate. ' ** '  instead of ' * ' because multiple path levels will follow /h2.
                .antMatchers(HttpMethod.POST, SecurityConstants.CREATE_REVIEW_PATH).permitAll()
                .antMatchers(HttpMethod.GET, SecurityConstants.GET_REVIEWS_BY_ISBN_PATH).permitAll()
                .anyRequest().authenticated())
            .addFilterBefore(new ExceptionHandlerFilter(), AuthenticationFilter.class)
            .addFilter(authenticationFilter)
            .addFilterAfter(new JWTAuthorizationFilter(userDetailsService()), AuthenticationFilter.class)
            .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }


}
