package com.dynamicportfolio.dynamicportfolio.configuration;

import com.dynamicportfolio.dynamicportfolio.filter.JwtRequestFilter;
import com.dynamicportfolio.dynamicportfolio.service.impl.CustomUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  @Qualifier("com.dynamicportfolio.dynamicportfolio.service.impl.CustomUserDetailsServiceImpl")
  private CustomUserDetailsServiceImpl userDetailsService;

  @Autowired
  @Qualifier("com.dynamicportfolio.dynamicportfolio.filter.JwtRequestFilter")
  private JwtRequestFilter jwtRequestFilter;


  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable().authorizeRequests().antMatchers("/dynamicportfolio/**").permitAll()
        .anyRequest().authenticated().and().sessionManagement().sessionCreationPolicy(
        SessionCreationPolicy.STATELESS);

    http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService);
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
