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
public class ManageableDTO {
    @JsonIgnore
    protected long id;
    protected String name;
    protected String cpf;
    protected Set<AddressDTO> addressesDTO = new HashSet<>();
    protected Set<ContactDTO> contactsDTO = new HashSet<>();
    protected String urlPhoto;
    protected Role role_id;
    protected Double salary;
    protected String oab;

    public ManageableDTO(Manageable manageable) {
        this.id = manageable.getId();
        this.name = manageable.getName();
        this.cpf = manageable.getCpf();
        this.urlPhoto = manageable.getUrlPhoto();
        this.role_id = manageable.getRole_Id();
        this.salary = manageable.getSalary();
    }

    public ManageableDTO(Manageable manageable, Set<Address> addresses, Set<Contact> contacts) {
        this(manageable);
        addresses.forEach(x -> this.addressesDTO.add(new AddressDTO(x)));
        contacts.forEach(x -> this.contactsDTO.add(new ContactDTO(x)));
    }
}
