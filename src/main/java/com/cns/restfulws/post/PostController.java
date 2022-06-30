package com.cns.restfulws.post;

import com.cns.restfulws.user.User;
import com.cns.restfulws.user.UserRepository;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class PostController {

    @Autowired
    PostRepository postRepository;

    @GetMapping(path = "/posts")
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @GetMapping(path = "/posts/{id}")
    public Optional<Post> getPostById(@PathVariable int id) {
        return postRepository.findById(id);
    }

    @DeleteMapping(path = "/posts/{id}")
    public void deletePostById(@PathVariable int id) {
        postRepository.deleteById(id);
    }

    @PostMapping(path = "/posts")
    public ResponseEntity createPost(@Valid @RequestBody Post post) {
        postRepository.save(post);
        URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(post.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
