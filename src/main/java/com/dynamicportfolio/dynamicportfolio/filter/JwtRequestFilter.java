package com.dynamicportfolio.dynamicportfolio.filter;

import com.dynamicportfolio.dynamicportfolio.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.dynamicportfolio.dynamicportfolio.common.Constants.BEARER;
import static com.dynamicportfolio.dynamicportfolio.common.Constants.BEARER_LENGTH;

@Component("com.dynamicportfolio.dynamicportfolio.filter.JwtRequestFilter")
public class JwtRequestFilter extends OncePerRequestFilter {

  @Autowired
  @Qualifier("com.dynamicportfolio.dynamicportfolio.service.impl.CustomUserDetailsServiceImpl")
  private org.springframework.security.core.userdetails.UserDetailsService customUserDetailsService;

  @Autowired
  @Qualifier("com.dynamicportfolio.dynamicportfolio.utils.JwtUtil")
  private JwtUtil jwtUtil;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    final String authorizationHeader = request.getHeader("Authorization");
    String username = null;
    String jwt = null;

    if (null != authorizationHeader && authorizationHeader.startsWith(BEARER)) {
      jwt = authorizationHeader.substring(BEARER_LENGTH);
      username = jwtUtil.extractUsername(jwt);
    }

    if (null != username && null == SecurityContextHolder.getContext().getAuthentication()) {
      UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
      if (jwtUtil.validateToken(jwt, userDetails)) {
        UsernamePasswordAuthenticationToken upat =
            new UsernamePasswordAuthenticationToken(userDetails, null,
                userDetails.getAuthorities());
        upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(upat);
      }
    }
    filterChain.doFilter(request,response);
  }
}
