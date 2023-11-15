package com.example.AdvocateLink.models;

import com.example.AdvocateLink.dto.ContactDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "contact")
@NoArgsConstructor
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private Long telephone;
    @ManyToOne
    private Manageable manageable;
    public Contact(ContactDTO contactDTO){
        this.id = contactDTO.getId();
        this.email = contactDTO.getEmail();
        this.telephone = contactDTO.getTelephone();
    }
}
