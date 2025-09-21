package com.conexxa.grupo_estudos.Controller;

import com.conexxa.grupo_estudos.DTO.LoginRequestDTO;
import com.conexxa.grupo_estudos.DTO.UserDetailResponseDTO;
import com.conexxa.grupo_estudos.Model.User;
import com.conexxa.grupo_estudos.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.conexxa.grupo_estudos.DTO.UserRequestDTO;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @Operation(summary = "Endpoint de teste")
    @ApiResponse(responseCode = "200", description = "API está online")
    @GetMapping("/ola")
    public String ola() {
        return "Olá!!!";
    }

    @Operation(summary = "Listar todos os usuários")
    @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso")
    @GetMapping("/all")
    public List<UserDetailResponseDTO> getAllUsers() {
        return service.getAllUsers().stream()
                .map(UserDetailResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Criar um novo usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos para o usuário")
    })
    @PostMapping
    public void postUser(@RequestBody UserRequestDTO userRequest) { // <<< MUDE AQUI
        service.postUser(userRequest); // <<< E AQUI
    }

    @Operation(summary = "Buscar um usuário pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário com o ID especificado não encontrado")
    })
    @GetMapping("/{id}")
    public UserDetailResponseDTO getUser(@PathVariable Long id) {
        User user = service.getUser(id);
        return new UserDetailResponseDTO(user);
    }

    @Operation(summary = "Atualizar um usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para atualização")
    })
    @PutMapping("/{id}")
    public User putUser(@PathVariable Long id, @RequestBody User user) {
        return service.putUser(id, user);
    }

    @Operation(summary = "Deletar um usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
    }

    @Operation(summary = "Realizar login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login bem-sucedido"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas (e-mail ou senha incorretos)")
    })
    @PostMapping("/login")
    public ResponseEntity<UserDetailResponseDTO> login(@RequestBody LoginRequestDTO loginRequest) {
        try {
            User user = service.login(loginRequest.getEmail(), loginRequest.getSenha());
            return ResponseEntity.ok(new UserDetailResponseDTO(user));
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).build();
        }
    }
}