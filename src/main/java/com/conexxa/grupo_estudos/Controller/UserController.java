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
        return "Olá!!!"; //Fiz isso aqui pra testar
    }



    @PostMapping
    public void postUser(@RequestBody User user) {

        service.postUser(user);

    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return service.getUser(id);
    }

    // Em conexxa_back-Spring/src/main/java/com/conexxa/grupo_estudos/Controller/UserController.java
    @PutMapping("/{id}") // Caminho que você mencionou
    public User putUser(@PathVariable Long id, @RequestBody User user) {
        return service.putUser(id,user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
    }
}