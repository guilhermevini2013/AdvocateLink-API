package com.example.AdvocateLink.models;

import com.example.AdvocateLink.dto.RoleDTO;
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameRole;

    public Role(RoleDTO dto) {
        this.id= dto.getId();
        this.nameRole= dto.getNameRole();
    }
}
