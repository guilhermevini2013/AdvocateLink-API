package com.example.AdvocateLink.dto;

import com.example.AdvocateLink.models.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {
    @JsonIgnore
    private Long id;
    private String name_Role;
    public RoleDTO(Role entity){
        this.id = entity.getId();
        this.name_Role = entity.getName_Role();
    }
}
