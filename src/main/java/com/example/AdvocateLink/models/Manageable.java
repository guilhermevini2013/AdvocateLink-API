package com.example.AdvocateLink.models;


import com.example.AdvocateLink.dto.AddressDTO;
import com.example.AdvocateLink.dto.ContactDTO;
import com.example.AdvocateLink.dto.ManageableDTO;
import com.example.AdvocateLink.dto.RoleDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Table(name = "manageable")
public class Manageable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String name;
    protected String cpf;
    @ManyToMany
    @JoinTable(name = "manageable_and_Address",
            joinColumns = @JoinColumn(name = "manageable_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id"))
    protected Set<Address> addresses = new HashSet<>();
    @ManyToMany
    @JoinTable(name = "manageable_and_Contact",
            joinColumns = @JoinColumn(name = "manageable_id"),
            inverseJoinColumns = @JoinColumn(name = "contacts_id"))
    protected Set<Contact> contacts = new HashSet<>();
    protected String urlPhoto;
    @ManyToOne
    @JoinColumn(name = "role_id")
    protected Role role_Id;
    protected double salary;

    public Manageable(Long id, String nome, String cpf, Set<Address> addresses, Set<Contact> contacts, String urlPhoto, Role role) {
        this.id = id;
        this.name = nome;
        this.cpf = cpf;
        this.addresses = addresses;
        this.contacts = contacts;
        this.urlPhoto = urlPhoto;
        this.role_Id = role;
    }

    public Manageable(ManageableDTO entityDTO) {
        this.id = entityDTO.getId();
        this.name = entityDTO.getName();
        this.cpf = entityDTO.getCpf();
        this.urlPhoto = entityDTO.getUrlPhoto();
        this.role_Id = entityDTO.getRole_id();
        this.salary = entityDTO.getSalary();
    }

    public Manageable(ManageableDTO manageableDTO, Set<AddressDTO> addressesDTO, Set<ContactDTO> contactsDTO) {
        this(manageableDTO);
        addressesDTO.forEach(x -> addresses.add(new Address(x)));
        contactsDTO.forEach(x -> contacts.add(new Contact(x)));
    }

    public void addAddresses(AddressDTO addressDTO) {
        addresses.add(new Address(addressDTO));
    }

    public void addContact(ContactDTO contactDTO) {
        contacts.add(new Contact(contactDTO));
    }
}
