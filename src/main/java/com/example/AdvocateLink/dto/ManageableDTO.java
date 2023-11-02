package com.example.AdvocateLink.dto;

import com.example.AdvocateLink.models.Address;
import com.example.AdvocateLink.models.Client;
import com.example.AdvocateLink.models.Contact;
import com.example.AdvocateLink.models.Manageable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ManageableDTO {
    protected long id;
    protected String name;
    protected String cpf;
    protected Set<AddressDTO> addressesDTO = new HashSet<>();
    protected Set<ContactDTO> contactsDTO = new HashSet<>();
    protected String urlPhoto;
    protected String role;
    protected double salary;
    protected String oab;

    public ManageableDTO(Manageable manageable ) {
        this.id = manageable.getId();
        this.name = manageable.getName();
        this.cpf = manageable.getCpf();
        this.urlPhoto = manageable.getUrlPhoto();
        this.role = manageable.getRole();
        this.salary = manageable.getSalary();
    }
    public ManageableDTO(Manageable manageable, Set<Address> addresses,Set<Contact> contacts) {
        this(manageable);
        addresses.forEach(x-> this.addressesDTO.add(new AddressDTO(x)));
        contacts.forEach(x-> this.contactsDTO.add(new ContactDTO(x)));
    }
}
