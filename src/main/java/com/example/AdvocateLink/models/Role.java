package com.example.AdvocateLink.models;

import com.example.AdvocateLink.dto.RoleDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "role")
public class Role {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name_Role;

    public Role(RoleDTO dto) {
        this.id= dto.getId();
        this.name_Role= dto.getName_Role();
    }
}
