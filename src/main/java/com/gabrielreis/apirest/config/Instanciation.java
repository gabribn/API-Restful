package com.gabrielreis.apirest.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.gabrielreis.apirest.domain.User;
import com.gabrielreis.apirest.repository.UserRepository;

@Configuration
public class Instanciation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		userRepository.deleteAll();
		
		User mariana = new User(null, "Mariana Luthor", "mariana@gmail.com");
		User alexandre = new User(null, "Alexandre Reis", "alex@gmail.com");
		User bob = new User(null, "Bob Marlei", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(mariana,alexandre,bob));
	}
	
}
