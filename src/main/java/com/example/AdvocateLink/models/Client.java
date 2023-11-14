package com.example.AdvocateLink.models;

import com.example.AdvocateLink.dto.AddressDTO;
import com.example.AdvocateLink.dto.ContactDTO;
import com.example.AdvocateLink.dto.ManageableDTO;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@DiscriminatorValue("Client")
public class Client extends Manageable{
    private String oab;

    public Client(Long id, String name, String cpf, String urlPhoto, Role role_id, String oab) {
        super(id, name, cpf, urlPhoto, role_id);
        this.oab = oab;
    }
//    public Client(ManageableDTO manageableDTO) {
//        super(manageableDTO.getId(), manageableDTO.getName(), manageableDTO.getCpf(), manageableDTO.getUrlPhoto(), manageableDTO.getRole_id());
//        this.oab=manageableDTO.getOab();
//    }
//    public Client(ManageableDTO manageableDTO, Set<AddressDTO> addressesDTO, Set<ContactDTO> contactsDTO){
//        this(manageableDTO);
//        addressesDTO.forEach(x-> super.addresses.add(new Address(x)));
//        contactsDTO.forEach(x-> super.contacts.add(new Contact(x)));
//    }
}
