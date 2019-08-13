package com.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {
    
    @Autowired
    private UserDAOService service;
    
    @GetMapping(path = "/users")
    public List<User> retrieveAllUsers() {
        if (service.findAll().size() == 0) {
            throw new UserNotFoundException("Not found");
        }
        return service.findAll();
    }
    
    @GetMapping(path = "/users/{id}")
    public Resource<User> retrieveUser(@PathVariable int id) {
        
        User user = service.findOne(id);
        if (user == null) {
            throw new UserNotFoundException("id- " + id);
            
        }
        
        // HATEOAS
        Resource<User> resource = new Resource<User>(user);
        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        
        resource.add(linkTo.withRel("all-users"));
        return resource;
    }
    
    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        
        User savedUser = service.save(user);
        
        // to generate the uri "/users/{id}
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
                .toUri();
        
        return ResponseEntity.created(location).build();
    }
    
    @DeleteMapping(path = "/users/{id}")
    public void deleteUser(@PathVariable int id) {
        
        User user = service.deleteById(id);
        if (user == null) {
            throw new UserNotFoundException("id- " + id);
            
        }
        
    }
    
}
