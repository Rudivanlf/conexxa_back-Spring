package com.conexxa.grupo_estudos.Controller;

import com.conexxa.grupo_estudos.Model.Group;
import com.conexxa.grupo_estudos.Service.GroupService;
import com.conexxa.grupo_estudos.DTO.GroupResponseDTO; // Importe o DTO
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors; // Importe o Collectors

@RestController
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @PostMapping
    public GroupResponseDTO createGroup(@RequestBody Group group) {
        Group newGroup = groupService.createGroup(group);
        return new GroupResponseDTO(newGroup); // Retorna o DTO
    }

    @GetMapping
    public List<GroupResponseDTO> getAllGroups() {
        return groupService.getAllGroups().stream()
                .map(GroupResponseDTO::new) // Converte cada Group para GroupResponseDTO
                .collect(Collectors.toList());
    }

    @GetMapping("/{groupId}")
    public GroupResponseDTO getGroupById(@PathVariable Long groupId) {
        Group group = groupService.getGroupById(groupId);
        return new GroupResponseDTO(group);
    }

    @PostMapping("/{groupId}/members/{userId}")
    public GroupResponseDTO addMember(@PathVariable Long groupId, @PathVariable Long userId) {
        Group updatedGroup = groupService.addMemberToGroup(groupId, userId);
        return new GroupResponseDTO(updatedGroup);
    }

    @DeleteMapping("/{groupId}/members/{userId}")
    public GroupResponseDTO removeMember(@PathVariable Long groupId, @PathVariable Long userId) {
        Group updatedGroup = groupService.removeMemberFromGroup(groupId, userId);
        return new GroupResponseDTO(updatedGroup);
    }


}