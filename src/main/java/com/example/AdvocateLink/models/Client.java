package com.example.AdvocateLink.models;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
public class Client extends Manageable{
    private String oab;
    public Client(long id, String nome, String cpf, Set<Address> addresses, Set<Contact> contacts, String urlPhoto, String role) {
        super(id, nome, cpf, addresses, contacts, urlPhoto, role);
    }

    public Client() {
    }
}
