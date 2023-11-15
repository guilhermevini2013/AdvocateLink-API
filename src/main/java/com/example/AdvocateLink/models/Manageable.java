package com.example.AdvocateLink.models;


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
@DiscriminatorColumn(name = "Dtype")
public abstract class Manageable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String name;
    protected String cpf;
    @OneToMany(mappedBy = "manageable", cascade = CascadeType.ALL)
    protected Set<Address> addresses = new HashSet<>();
    @OneToMany(mappedBy = "manageable", cascade = CascadeType.ALL)
    protected Set<Contact> contacts = new HashSet<>();
    protected String urlPhoto;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "role_id")
    protected Role role_Id;

    public Manageable(Long id, String name, String cpf, String urlPhoto, Role role_id) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.urlPhoto = urlPhoto;
        this.role_Id = role_id;
    }
}
