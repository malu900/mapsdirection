package com.location.maps.config;

import com.location.maps.service.CustomUserDetailsService;
import com.location.maps.security.JwtAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import com.location.maps.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
//This is the primary spring security annotation that is used to enable web security in a project.
@EnableWebSecurity
//This is used to enable method level security based on annotations.
@EnableGlobalMethodSecurity(
        // the @Secured annotation using which you can protect your controller/service method
        securedEnabled = true,
        // enables the @RolesAllowed
        jsr250Enabled = true,
        //  It enables more complex expression based access control syntax with @PreAuthorize and @PostAuthorize annotations
        prePostEnabled = true
)
// provides default security configurations and allows other classes to extend it
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // To authenticate a User or perform various role-based checks, Spring security needs to load users details somehow.
    final CustomUserDetailsService customUserDetailsService;
    // We’ll use JWTAuthenticationFilter to implement a filter that -
    //reads JWT authentication token from the Authorization header of all the requests
    //validates the token
    //loads the user details associated with that token.
    //Sets the user details in Spring Security’s SecurityContext.
    // Spring Security uses the user details to perform authorization checks.
    // We can also access the user details stored in the SecurityContext in our controllers to perform our business logic.
    private final JwtAuthenticationEntryPoint unauthorizedHandler;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService, JwtAuthenticationEntryPoint unauthorizedHandler) {
        this.customUserDetailsService = customUserDetailsService;
        this.unauthorizedHandler = unauthorizedHandler;
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    // AuthenticationManagerBuilder is used to create an AuthenticationManager
    // instance which is the main Spring Security interface for authenticating a user.
    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    // AuthenticationManagerBuilder to build in-memory authentication, LDAP authentication,
    // JDBC authentication, or add your custom authentication provider
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
//    customUserDetailsService and a passwordEncoder to build the AuthenticationManager.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // The HttpSecurity configurations are used to configure security functionalities like
    // csrf, sessionManagement, and add rules to protect resources based on various conditions.
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/",
                        "/favicon.ico",
                        "/**/*.png",
                        "/**/*.gif",
                        "/**/*.svg",
                        "/**/*.jpg",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js")
                .permitAll()
                .antMatchers("/api/auth/**")
                .permitAll()
                .antMatchers("/api/user/checkUsernameAvailability", "/api/user/checkEmailAvailability")
                .permitAll()
                .antMatchers(HttpMethod.GET, "/api/users/**")
                .permitAll()
                .anyRequest()
                .authenticated();

        // Add custom JWT security filter
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

    }
}