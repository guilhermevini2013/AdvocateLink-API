package com.example.AdvocateLink.models;

import com.example.AdvocateLink.dto.AddressDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "Address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private int number;
    private String burgh;
    @ManyToOne
    @JoinColumn()
    private Manageable manageable;
    public Address(AddressDTO addressDTO){
        this.id = addressDTO.getId();
        this.street = addressDTO.getStreet();
        this.number = addressDTO.getNumber();
        this.burgh = addressDTO.getStreet();
    }
}
