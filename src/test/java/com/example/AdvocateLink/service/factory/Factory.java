package com.example.AdvocateLink.service.factory;

import com.example.AdvocateLink.dto.ManageableDTO;
import com.example.AdvocateLink.dto.RoleDTO;
import com.example.AdvocateLink.models.Manageable;
import com.example.AdvocateLink.models.Role;

public class Factory {
    public static Manageable createManageable() {
        return null;
    }

    public static ManageableDTO createManageableDTO() {
        return null;
    }
    public static Role createRole(){
        return new Role(1l,"Engenheiro");
    }
    public static RoleDTO createRoleDto(){
        return new RoleDTO(createRole());
    }
    public static RoleDTO createRoleDtoNullAttributes(){
        return new RoleDTO(2l,null);
    }
}
