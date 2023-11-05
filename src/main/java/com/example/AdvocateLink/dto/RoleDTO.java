package com.example.AdvocateLink.dto;

import com.example.AdvocateLink.models.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {
    private Long id;
    private String nameRole;
    public RoleDTO(Role entity){
        this.id = entity.getId();
        this.nameRole = entity.getNameRole();
    }
}
