package com.example.AdvocateLink.dto;

import com.example.AdvocateLink.models.Address;
import com.example.AdvocateLink.models.Contact;
import com.example.AdvocateLink.models.Manageable;
import com.example.AdvocateLink.models.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class ManageableDTO {
    @JsonIgnore
    protected long id;
    protected String name;
    protected String cpf;
    protected Set<AddressDTO> addressesDTO = new HashSet<>();
    protected Set<ContactDTO> contactsDTO = new HashSet<>();
    protected String urlPhoto;
    protected Role role_id;

    public ManageableDTO(long id, String name, String cpf, String urlPhoto, Role role_id) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.urlPhoto = urlPhoto;
        this.role_id = role_id;
    }
}
