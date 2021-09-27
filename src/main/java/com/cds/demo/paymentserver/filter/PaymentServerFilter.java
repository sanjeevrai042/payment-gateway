package com.cds.demo.paymentserver.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cds.demo.paymentserver.service.PaymentUserDetailService;
import com.cds.demo.paymentserver.util.JWTUtil;

@Component
public class PaymentServerFilter extends OncePerRequestFilter {

	private static final Logger logger = LoggerFactory.getLogger(PaymentServerFilter.class);
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private PaymentUserDetailService userDetailService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		logger.info("pre-filter executed");
		String authorizationHeader = request.getHeader("Authorization");
		String userName = null;
		String jwt = null;
		if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			jwt =  authorizationHeader.substring(7);
			userName =  jwtUtil.extractUserName(jwt);
		}
		if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = userDetailService.loadUserByUsername(userName);
			if(jwtUtil.validateToken(jwt, userDetails)) {
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
				
			}
		}
		filterChain.doFilter(request, response);
	}

}
