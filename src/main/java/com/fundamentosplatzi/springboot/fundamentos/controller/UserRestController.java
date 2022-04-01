package com.fundamentosplatzi.springboot.fundamentos.controller;

import com.fundamentosplatzi.springboot.fundamentos.caseuse.CreateUser;
import com.fundamentosplatzi.springboot.fundamentos.caseuse.DeleteUser;
import com.fundamentosplatzi.springboot.fundamentos.caseuse.GetUser;
import com.fundamentosplatzi.springboot.fundamentos.caseuse.UpdateUser;
import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import com.fundamentosplatzi.springboot.fundamentos.repository.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private GetUser getUser;

    private UpdateUser updateUser;

    private CreateUser createUser;

    private DeleteUser deleteUser;

    private UserRepository userRepository;

    public UserRestController(GetUser getUser, UpdateUser updateUser, CreateUser createUser, DeleteUser deleteUser,
                              UserRepository userRepository) {
        this.getUser = getUser;
        this.updateUser = updateUser;
        this.createUser = createUser;
        this.deleteUser = deleteUser;
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    List<User> get() {
        return getUser.getAll();
    }

    @PostMapping("/")
    ResponseEntity<User> newUser(@RequestBody User newUser) {
        return new ResponseEntity<>(createUser.save(newUser), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteUser(@PathVariable Long id) {
        deleteUser.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    ResponseEntity<User> replaceUser(@RequestBody User upUser, @PathVariable Long id) {
        return new ResponseEntity<>(updateUser.replaceUser(upUser), HttpStatus.OK);
    }

    @GetMapping("/pageable")
    List<User> getUserPageable(@RequestParam int page, @RequestParam int size) {
        return userRepository.findAll(PageRequest.of(page, size)).getContent();
    }
}
