package com.conexxa.grupo_estudos.Repository;

import com.conexxa.grupo_estudos.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}