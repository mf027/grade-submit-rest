package com.mircea.gradesubmitrest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private static final String ADMIN_ROLE = "ADMIN";
    private static final String USER_ROLE = "USER";

    @Value("${spring.admin.name}")
    private String ADMIN_USER_NAME;
    @Value("${spring.admin.pass}")
    private String ADMIN_PASS;

    @Value("${spring.user.name}")
    private String USER_USER_NAME;
    @Value("${spring.user.pass}")
    private String USER_PASS;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        .csrf().disable()
        .authorizeRequests()
        .antMatchers(HttpMethod.DELETE).hasRole(ADMIN_ROLE)
        .antMatchers(HttpMethod.PUT).hasRole(ADMIN_ROLE)
        .antMatchers(HttpMethod.POST).hasAnyRole(ADMIN_ROLE, USER_ROLE)
        .antMatchers(HttpMethod.GET).permitAll()
        .anyRequest().authenticated()
        .and()
        .httpBasic()
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
}


    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails admin = User.builder()
        .username(ADMIN_USER_NAME)
        .password(passwordEncoder.encode(ADMIN_PASS))
        .roles(ADMIN_ROLE)
        .build();

        UserDetails user = User.builder()
        .username(USER_USER_NAME)
        .password(passwordEncoder.encode(USER_PASS))
        .roles(USER_ROLE)
        .build();

        return new InMemoryUserDetailsManager(admin, user);
        
    }


}
