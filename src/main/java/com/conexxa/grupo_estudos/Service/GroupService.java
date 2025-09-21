package com.conexxa.grupo_estudos.Service;

import com.conexxa.grupo_estudos.Model.Group;
import com.conexxa.grupo_estudos.Model.User; // Importe a classe User
import com.conexxa.grupo_estudos.Repository.GroupRepository;
import com.conexxa.grupo_estudos.Repository.UserRepository; // Importe o UserRepository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.conexxa.grupo_estudos.DTO.GroupRequestDTO;
import com.conexxa.grupo_estudos.DTO.GroupUpdateRequestDTO;
import java.util.Collections; // Importe a classe Collections
import java.util.HashSet; // Importe a classe HashSet
import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository; // Injete o UserRepository

    public Group createGroup(GroupRequestDTO groupRequest) {
        User criador = userRepository.findById(groupRequest.getCriadorId())
                .orElseThrow(() -> new RuntimeException("Criador do grupo não encontrado!"));

        Group group = new Group();
        group.setNome(groupRequest.getNome());
        group.setDescricao(groupRequest.getDescricao());
        group.setCriador(criador);
        group.setMembros(new HashSet<>(Collections.singleton(criador)));
        criador.getGrupos().add(group);

        return groupRepository.save(group);
    }

    public Group updateGroup(Long groupId, GroupUpdateRequestDTO groupRequest) {
        Group group = getGroupById(groupId); // Reutiliza o method de busca
        group.setNome(groupRequest.getNome());
        group.setDescricao(groupRequest.getDescricao());
        return groupRepository.save(group);
    }

    public Group addMemberToGroup(Long groupId, Long userId) {
        // Encontra o grupo ou lança uma exceção
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Grupo não encontrado!"));

        // Encontra o usuário ou lança uma exceção
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        // Adiciona o usuário aos membros do grupo
        group.getMembros().add(user);

        // Adiciona o grupo à lista de grupos do usuário
        user.getGrupos().add(group);

        // Salva o grupo (que gerencia a relação)
        return groupRepository.save(group);
    }

    public Group removeMemberFromGroup(Long groupId, Long userId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Grupo não encontrado!"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        // Remove o usuário dos membros do grupo
        group.getMembros().remove(user);

        // Remove o grupo da lista de grupos do usuário
        user.getGrupos().remove(group);

        return groupRepository.save(group);
    }

    public Group getGroupById(Long groupId) {
        return groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Grupo não encontrado!"));
    }



    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }
}