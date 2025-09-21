package com.conexxa.grupo_estudos.Service;

import com.conexxa.grupo_estudos.Model.Group;
import com.conexxa.grupo_estudos.Model.User; // Importe a classe User
import com.conexxa.grupo_estudos.Repository.GroupRepository;
import com.conexxa.grupo_estudos.Repository.UserRepository; // Importe o UserRepository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections; // Importe a classe Collections
import java.util.HashSet; // Importe a classe HashSet
import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository; // Injete o UserRepository

    public Group createGroup(Group group) {
        // Busca o usuário completo pelo ID fornecido no JSON
        User criador = userRepository.findById(group.getCriador().getId())
                .orElseThrow(() -> new RuntimeException("Criador do grupo não encontrado!"));

        group.setCriador(criador);

        // Adiciona o criador à lista de membros do grupo
        group.setMembros(new HashSet<>(Collections.singleton(criador)));

        // --- INÍCIO DA NOVA LINHA ---
        // Adiciona o novo grupo à lista de grupos do usuário criador
        criador.getGrupos().add(group);
        // --- FIM DA NOVA LINHA ---

        return groupRepository.save(group);
    }

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }
}