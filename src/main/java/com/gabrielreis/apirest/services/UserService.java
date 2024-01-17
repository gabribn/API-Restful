package com.gabrielreis.apirest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielreis.apirest.domain.User;
import com.gabrielreis.apirest.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository repo;
	
	public List<User> findAll(){
		return repo.findAll();
	}
}
