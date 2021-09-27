package com.cds.demo.paymentserver.service;

import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cds.demo.paymentserver.dao.UserRepository;
import com.cds.demo.paymentserver.exception.PaymentException;

@Service
public class PaymentUserDetailService implements UserDetailsService {
	
	private static final Logger logger = LoggerFactory.getLogger(PaymentUserDetailService.class);

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<com.cds.demo.paymentserver.entity.User> user= userRepository.findById(userName);
		if (user.isPresent()) {
			logger.info("user  found ");
			return new User(user.get().getUsername(), user.get().getPassword(), new ArrayList<>());
		}
		throw new PaymentException("No user found");
	}
}
