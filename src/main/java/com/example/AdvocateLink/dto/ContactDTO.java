package com.example.AdvocateLink.dto;

import com.example.AdvocateLink.models.Contact;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ContactDTO {
    @JsonIgnore
    private Long id;
    private String email;
    private Long telephone;

    public ContactDTO(Contact entity) {
        this.id = entity.getId();
        this.email = entity.getEmail();
        this.telephone = entity.getTelephone();
    }
}
