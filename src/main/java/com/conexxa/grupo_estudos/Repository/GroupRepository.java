package com.conexxa.grupo_estudos.Repository;

import com.conexxa.grupo_estudos.Model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
}