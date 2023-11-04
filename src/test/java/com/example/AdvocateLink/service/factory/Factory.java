package com.example.AdvocateLink.service.factory;

import com.example.AdvocateLink.dto.ManageableDTO;
import com.example.AdvocateLink.models.Manageable;

public class Factory {
    public static Manageable createManageable(){
        return new Manageable(3l,"Henrique Cardoso","123",null,null,"www","test");
    }
    public static ManageableDTO createManageableDTO(){
        return new ManageableDTO(createManageable());
    }
}
