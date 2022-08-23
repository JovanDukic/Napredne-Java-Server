package com.app.springbootmavenapp.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import com.app.springbootmavenapp.service.CustomUserDetailService;
import com.app.springbootmavenapp.util.JwtUtil;

//@Component
//@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

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

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private CustomUserDetailService userDetailService;

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;

		String authorizationHeader = request.getHeader("Authorization");

		String token = null;
		String username = null;

		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
			token = authorizationHeader.substring(7);
			username = jwtUtil.extractUsername(token);
		}

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = userDetailService.loadUserByUsername(username);

			if (jwtUtil.validateToken(token, userDetails)) {
				if (jwtUtil.validateToken(token, userDetails)) {

					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					usernamePasswordAuthenticationToken
							.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
			}
		}

		response.setHeader("Access-Control-Allow-Origin", allowedOrigins);
		response.setHeader("Access-Control-Allow-Credentials", allowCredentials);
		response.setHeader("Access-Control-Allow-Methods", allowedMethods);
		response.setHeader("Access-Control-Max-Age", maxAge);
		response.setHeader("Access-Control-Allow-Headers", allowedHeaders);

		if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			chain.doFilter(req, res);
		}

	}

}
