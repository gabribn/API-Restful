package com.gabrielreis.apirest.resources;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gabrielreis.apirest.domain.Post;
import com.gabrielreis.apirest.domain.User;
import com.gabrielreis.apirest.resources.util.URL;
import com.gabrielreis.apirest.services.PostService;
import com.gabrielreis.apirest.services.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(value="/posts")
public class PostResource {
	
	@Autowired
	private PostService service;
	@Autowired
	private UserService userService;
	
	@GetMapping("/{id}")
 	public ResponseEntity<Post> findById(@PathVariable String id) {
		Post obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping("/titlesearch")
 	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text", defaultValue="") String text) {
		text = URL.decodeParam(text);
		List<Post> list = service.findByTitle(text);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping
 	public ResponseEntity<List<Post>> getAll() {
		List<Post> allPosts = service.findAll();
		return ResponseEntity.ok().body(allPosts);
	}
	
	@PostMapping
    public ResponseEntity<Post> insert(@RequestBody Post post) {
        Post newPost = service.insert(post);
        User user = userService.findById(post.getAuthor().getId());
        user.getPosts().addAll(Arrays.asList(newPost));
        userService.update(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newPost.getId()).toUri();
        return ResponseEntity.created(uri).body(newPost);
    }
}
