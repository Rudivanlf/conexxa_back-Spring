package com.conexxa.grupo_estudos.Service;

import com.conexxa.grupo_estudos.Model.User;
import com.conexxa.grupo_estudos.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public void postUser(User user) {
        repository.save(user);
    }

    public User getUser(Long id) {
        return repository.findById(id).orElse(null);
    }

    // Em conexxa_back-Spring/src/main/java/com/conexxa/grupo_estudos/Service/UserService.java
    public User putUser(Long id, User user) { // Recebe o ID e os dados
        return repository.findById(id)
                .map(userExist -> {
                    userExist.setNome(user.getNome());
                    userExist.setEmail(user.getEmail());
                    userExist.setSenhaHash(user.getSenhaHash());
                    userExist.setCurso(user.getCurso()); // Adicionei o curso também
                    return repository.save(userExist);
                })
                .orElse(null); // Retorna null se o usuário não for encontrado
    }

    public void deleteUser(Long id) {
        repository.deleteById(id);
    }
}