package com.conexxa.grupo_estudos.Service;

import com.conexxa.grupo_estudos.Model.User;
import com.conexxa.grupo_estudos.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
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

    public List<User> getAllUsers() { //novo metodo para pegar todos os usuarios do banco
        return repository.findAll();
    }

    public void deleteUser(Long id) {
        repository.deleteById(id);
    }
}