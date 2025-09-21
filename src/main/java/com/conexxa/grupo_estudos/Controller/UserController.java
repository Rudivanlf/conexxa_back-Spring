package com.conexxa.grupo_estudos.Controller;

import com.conexxa.grupo_estudos.DTO.UserDetailResponseDTO;
import com.conexxa.grupo_estudos.Model.User;
import com.conexxa.grupo_estudos.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors; // Adicione esta importação
import com.conexxa.grupo_estudos.DTO.LoginRequestDTO;
import com.conexxa.grupo_estudos.DTO.UserDetailResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/ola")
    public String ola() {
        return "Olá!!!"; //Fiz isso aqui pra testar
    }

    @GetMapping("/all")
    public List<UserDetailResponseDTO> getAllUsers() {
        return service.getAllUsers().stream()
                .map(UserDetailResponseDTO::new)
                .collect(Collectors.toList());
    }


    @PostMapping
    public void postUser(@RequestBody User user) {

        service.postUser(user);

    }

    @GetMapping("/{id}")
    public UserDetailResponseDTO getUser(@PathVariable Long id) {
        User user = service.getUser(id);
        // Retorna o DTO em vez da entidade para quebrar o loop
        return new UserDetailResponseDTO(user);
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

    @PostMapping("/login")
    public ResponseEntity<UserDetailResponseDTO> login(@RequestBody LoginRequestDTO loginRequest) {
        try {
            User user = service.login(loginRequest.getEmail(), loginRequest.getSenha());
            // Se o login for bem-sucedido, retorna os dados do usuário (sem a senha)
            return ResponseEntity.ok(new UserDetailResponseDTO(user));
        } catch (RuntimeException e) {
            // Se o login falhar (usuário não encontrado ou senha inválida), retorna "Não Autorizado"
            return ResponseEntity.status(401).build();
        }
    }
}