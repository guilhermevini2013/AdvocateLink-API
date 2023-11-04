package com.example.AdvocateLink.models;


import com.example.AdvocateLink.dto.ManageableDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
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
    protected String role;
    protected double salary;

    public Manageable(Long id, String nome, String cpf, Set<Address> addresses, Set<Contact> contacts, String urlPhoto, String role) {
        this.id = id;
        this.name = nome;
        this.cpf = cpf;
        this.addresses = addresses;
        this.contacts = contacts;
        this.urlPhoto = urlPhoto;
        this.role = role;
    }

    public Manageable(ManageableDTO entityDTO) {
        this.id = entityDTO.getId();
        this.name = entityDTO.getName();
        this.cpf = entityDTO.getCpf();
        this.urlPhoto = entityDTO.getUrlPhoto();
        this.role = entityDTO.getRole();
        this.salary = entityDTO.getSalary();
    }
}
