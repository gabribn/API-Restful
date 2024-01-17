package com.gabrielreis.apirest.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielreis.apirest.domain.User;

@RestController
@RequestMapping(value="/users")
public class UserResource {
	@GetMapping
	public ResponseEntity <List<User>> findAll(){
		User mario = new User("1","Mario Silva","mario@gmail.com");
		User alex = new User("2","Alex Reis","alex@gmail.com");
		List<User> list = new ArrayList<>();
		list.addAll(Arrays.asList(mario, alex));
		return ResponseEntity.ok().body(list);
	}
}
