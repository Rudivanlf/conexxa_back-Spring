package com.conexxa.grupo_estudos.Controller;

import com.conexxa.grupo_estudos.DTO.GroupResponseDTO;
import com.conexxa.grupo_estudos.Model.Group;
import com.conexxa.grupo_estudos.Service.GroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.conexxa.grupo_estudos.DTO.GroupRequestDTO;
import com.conexxa.grupo_estudos.DTO.GroupUpdateRequestDTO;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @Operation(summary = "Criar um novo grupo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Grupo criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para a criação do grupo")
    })
    @PostMapping
    public GroupResponseDTO createGroup(@RequestBody GroupRequestDTO groupRequest) {
        Group newGroup = groupService.createGroup(groupRequest);
        return new GroupResponseDTO(newGroup);
    }

    @Operation(summary = "Atualizar nome e descrição de um grupo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Grupo atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Grupo não encontrado")
    })
    @PutMapping("/{groupId}")
    public GroupResponseDTO updateGroup(@PathVariable Long groupId, @RequestBody GroupUpdateRequestDTO groupRequest) {
        Group updatedGroup = groupService.updateGroup(groupId, groupRequest);
        return new GroupResponseDTO(updatedGroup);
    }

    @Operation(summary = "Listar todos os grupos")
    @ApiResponse(responseCode = "200", description = "Lista de grupos retornada com sucesso")
    @GetMapping
    public List<GroupResponseDTO> getAllGroups() {
        return groupService.getAllGroups().stream()
                .map(GroupResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Buscar um grupo pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Grupo encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Grupo com o ID especificado não encontrado")
    })
    @GetMapping("/{groupId}")
    public GroupResponseDTO getGroupById(@PathVariable Long groupId) {
        Group group = groupService.getGroupById(groupId);
        return new GroupResponseDTO(group);
    }

    @Operation(summary = "Adicionar um membro a um grupo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Membro adicionado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Grupo ou usuário não encontrado")
    })
    @PostMapping("/{groupId}/members/{userId}")
    public GroupResponseDTO addMember(@PathVariable Long groupId, @PathVariable Long userId) {
        Group updatedGroup = groupService.addMemberToGroup(groupId, userId);
        return new GroupResponseDTO(updatedGroup);
    }

    @Operation(summary = "Remover um membro de um grupo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Membro removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Grupo ou usuário não encontrado")
    })
    @DeleteMapping("/{groupId}/members/{userId}")
    public GroupResponseDTO removeMember(@PathVariable Long groupId, @PathVariable Long userId) {
        Group updatedGroup = groupService.removeMemberFromGroup(groupId, userId);
        return new GroupResponseDTO(updatedGroup);
    }
}