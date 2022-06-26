package com.cns.restfulws.user;

import com.cns.restfulws.exceptions.CustomNotFoundException;
import com.sun.jndi.toolkit.url.Uri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserDaoService userDaoService;

    @GetMapping(path = "/users")
    public List<User> getAllUsers() {
        return userDaoService.findAll();
    }

    @GetMapping(path = "/users/{id}")
    public EntityModel<User> getUserById(@PathVariable int id) {
        User user = userDaoService.findOne(id);
        EntityModel<User> model = EntityModel.of(user);
        WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers());
        model.add(link.withRel("All Users"));
        return model;
    }

    @DeleteMapping(path = "/users/{id}")
    public List<User> deleteUserById(@PathVariable int id) {
        return userDaoService.deleteOne(id);
    }

    @PostMapping(path = "/users")
    public ResponseEntity createUser(@Valid @RequestBody User user) {
        userDaoService.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
