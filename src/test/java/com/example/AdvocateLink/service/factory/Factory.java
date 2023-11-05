package com.example.AdvocateLink.service.factory;

import com.example.AdvocateLink.dto.ManageableDTO;
import com.example.AdvocateLink.models.Manageable;
import com.example.AdvocateLink.models.Role;

public class Factory {
    public static Manageable createManageable() {
        return new Manageable(3l, "Henrique Cardoso", "123", null, null, "www", new Role(1l,"test"));
    }

    public static ManageableDTO createManageableDTO() {
        return new ManageableDTO(createManageable());
    }
}
