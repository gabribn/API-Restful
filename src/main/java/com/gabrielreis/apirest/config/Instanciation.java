package com.gabrielreis.apirest.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.gabrielreis.apirest.domain.Post;
import com.gabrielreis.apirest.domain.User;
import com.gabrielreis.apirest.dto.AuthorDTO;
import com.gabrielreis.apirest.repository.PostRepository;
import com.gabrielreis.apirest.repository.UserRepository;

@Configuration
public class Instanciation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		User mariana = new User(null, "Mariana Luthor", "mariana@gmail.com");
		User alexandre = new User(null, "Alexandre Reis", "alex@gmail.com");
		User bob = new User(null, "Bob Marlei", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(mariana,alexandre,bob));
		
		Post post1 = new Post(null, sdf.parse("22/04/2018"), "Testando o post", "Testezinho testador :)", new AuthorDTO(mariana));
		Post post2 = new Post(null, sdf.parse("24/04/2018"), "Testei", "O teste do teste deu certo?", new AuthorDTO(mariana));
		
		postRepository.saveAll(Arrays.asList(post1,post2));
	}
	
}
