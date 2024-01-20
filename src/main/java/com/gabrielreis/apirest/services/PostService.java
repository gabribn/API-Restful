package com.gabrielreis.apirest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielreis.apirest.domain.Post;
import com.gabrielreis.apirest.repository.PostRepository;
import com.gabrielreis.apirest.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	@Autowired
	private PostRepository repo;

	public Post findById(String id) {
		Optional<Post> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
	}

	public List<Post> findByTitle(String text) {
		return repo.searchTitle(text);
	}

	public List<Post> findAll() {
		return repo.findAll();
	}
	
	public Post insert(Post post) {
        return repo.save(post);
    }
}
