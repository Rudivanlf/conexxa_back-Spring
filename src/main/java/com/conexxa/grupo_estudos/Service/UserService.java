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

    public User putUser(User user) {
        User userExist = repository.findById(user.getId()).orElse(null);
        if(Objects.nonNull(userExist)) {
            userExist.setUsername(user.getUsername());
            userExist.setEmail(user.getEmail());
            userExist.setPassword(user.getPassword());
            repository.save(userExist);
        }
        return null;
    }

    public void deleteUser(Long id) {
        repository.deleteById(id);
    }
}