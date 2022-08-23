package com.app.springbootmavenapp.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.app.springbootmavenapp.service.CustomUserDetailService;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	private com.app.springbootmavenapp.util.JwtUtil jwtUtil;

	@Autowired
	private CustomUserDetailService service;

	@Value("${endpoints.cors.allowed-origins}")
	private String allowedOrigins;

	@Value("${endpoints.cors.allowed-methods}")
	private String allowedMethods;

	@Value("${endpoints.cors.allow-credentials}")
	private String allowCredentials;

	@Value("${endpoints.cors.allowed-headers}")
	private String allowedHeaders;

	@Value("${endpoints.cors.max-age}")
	private String maxAge;

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			FilterChain filterChain) throws ServletException, IOException {

		String authorizationHeader = httpServletRequest.getHeader("Authorization");

		String token = null;
		String userName = null;

		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			token = authorizationHeader.substring(7);

			try {
				userName = jwtUtil.extractUsername(token);
			} catch (ExpiredJwtException e) {
				System.out.println("Authorization token has expited: " + e.getMessage());
			}
		}

		if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = service.loadUserByUsername(userName);

			if (jwtUtil.validateToken(token, userDetails)) {

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}

		httpServletResponse.setHeader("Access-Control-Allow-Origin", allowedOrigins);
		httpServletResponse.setHeader("Access-Control-Allow-Credentials", allowCredentials);
		httpServletResponse.setHeader("Access-Control-Allow-Methods", allowedMethods);
		httpServletResponse.setHeader("Access-Control-Max-Age", maxAge);
		httpServletResponse.setHeader("Access-Control-Allow-Headers", allowedHeaders);

		if ("OPTIONS".equalsIgnoreCase(httpServletRequest.getMethod())) {
			httpServletResponse.setStatus(HttpServletResponse.SC_OK);
		} else {
			filterChain.doFilter(httpServletRequest, httpServletResponse);
		}
	}
}
