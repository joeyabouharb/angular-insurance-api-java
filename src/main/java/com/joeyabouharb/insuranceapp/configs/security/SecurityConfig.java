package com.joeyabouharb.insuranceapp.configs.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.joeyabouharb.insuranceapp.security.CustomUserDetailsService;
import com.joeyabouharb.insuranceapp.security.JwtAuthenticationFilter;
import com.joeyabouharb.insuranceapp.security.JwtEntryPoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.security.config.BeanIds;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


  @Autowired
  CustomUserDetailsService customUserDetailsService;

  @Autowired
  private JwtEntryPoint unauthorizedHandler;

  @Bean
  public JwtAuthenticationFilter jwtAuthenticationFilter() {
      return new JwtAuthenticationFilter();
  }
  
  @Override
protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
  auth.userDetailsService(customUserDetailsService)
  .passwordEncoder(encoder());
}
 
@Bean(BeanIds.AUTHENTICATION_MANAGER)
@Override
public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
}

@Bean
public PasswordEncoder  encoder() {
    return new BCryptPasswordEncoder();
}

@Override
protected void configure(HttpSecurity http) throws Exception { 
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
    .authorizeRequests()
    .antMatchers("/api/user/**").permitAll()
    .antMatchers("/api/claim/list").authenticated()
    .and().httpBasic()
    .and()
    .cors()
    .and()
    .csrf().disable();
     // Add our custom JWT security filter
     http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);;
}


@Bean
public CorsConfigurationSource corsConfigurationSource() {
    final CorsConfiguration configuration = new CorsConfiguration();
    configuration.addAllowedOrigin("https://claim-app-angular.herokuapp.com/");
    List<String> methods = new ArrayList<String>(Arrays.asList("HEAD",
    "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
    configuration.setAllowedMethods(methods);
    // setAllowCredentials(true) is important, otherwise:
    // The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
    configuration.setAllowCredentials(true);
    // setAllowedHeaders is important! Without it, OPTIONS preflight request
    // will fail with 403 Invalid CORS request
    configuration.setAllowedHeaders(Arrays.asList(
      "Authorization",
       "Cache-Control",
        "Content-Type",
        "Access-Control-Request-Headers",
        "Access-Control-Allow-Origin",
        "Access-Control-Request-Method",
        "Origin",
        "Referrer",
        "User-Agent"));
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
}


}
