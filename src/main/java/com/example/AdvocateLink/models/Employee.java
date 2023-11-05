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
public class Employee extends Manageable{
    public Employee(long id, String nome, String cpf, Set<Address> addresses, Set<Contact> contacts, String urlPhoto, Role role, double salary) {
        super(id, nome, cpf, addresses, contacts, urlPhoto, role, salary);
    }
    public Employee() {
    }
}
