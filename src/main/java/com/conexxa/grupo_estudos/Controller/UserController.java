package com.conexxa.grupo_estudos.Controller;

import com.conexxa.grupo_estudos.Model.User;
import com.conexxa.grupo_estudos.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/ola")
    public String ola() {
        return "Olá!!!";
    }

    @PostMapping
    public void postUser(@RequestBody User user) {
        service.postUser(user);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return service.getUser(id);
    }

    @PutMapping()
    public User putUser(@RequestBody User user) {
        return service.putUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
    }
}