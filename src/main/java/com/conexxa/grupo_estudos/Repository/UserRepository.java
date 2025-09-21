package com.conexxa.grupo_estudos.Repository;

import com.conexxa.grupo_estudos.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional; // Importe a classe Optional

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Spring Data JPA criará a query automaticamente a partir do nome do method
    Optional<User> findByEmail(String email);
}