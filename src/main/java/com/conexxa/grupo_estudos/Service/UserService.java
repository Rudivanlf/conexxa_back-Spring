package com.conexxa.grupo_estudos.Service;

import com.conexxa.grupo_estudos.Model.User;
import com.conexxa.grupo_estudos.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import com.conexxa.grupo_estudos.DTO.UserRequestDTO;

@Service
public class UserService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public void postUser(UserRequestDTO userRequest) {
        // Crie uma nova entidade User
        User user = new User();

        // Transfira os dados do DTO para a entidade
        user.setNome(userRequest.getNome());
        user.setEmail(userRequest.getEmail());
        user.setSenhaHash(userRequest.getSenhaHash());
        user.setCurso(userRequest.getCurso());

        // Salve a entidade no banco de dados
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

    public User login(String email, String senha) {
        // 1. Encontra o usuário pelo email
        User user = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        // 2. Compara a senha fornecida com a senha armazenada (senhaHash)
        // ATENÇÃO: Esta é uma comparação de texto simples, não segura para produção.
        if (user.getSenhaHash().equals(senha)) {
            return user; // Se a senha corresponder, retorna o usuário
        } else {
            // Se a senha não corresponder, lança uma exceção
            throw new RuntimeException("Senha inválida!");
        }
    }
}