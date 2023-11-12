package com.example.AdvocateLink.dto;

import com.example.AdvocateLink.models.Address;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO{
    @JsonIgnore
    private Long id;
    private String street;
    private int number;
    private String burgh;

    public AddressDTO(Address entity) {
        this.id = entity.getId();
        this.street = entity.getStreet();
        this.number = entity.getNumber();
        this.burgh = entity.getBurgh();
    }
}
