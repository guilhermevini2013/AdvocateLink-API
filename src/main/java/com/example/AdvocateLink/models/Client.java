package com.example.AdvocateLink.models;

import com.example.AdvocateLink.dto.AddressDTO;
import com.example.AdvocateLink.dto.ClientDTO;
import com.example.AdvocateLink.dto.ContactDTO;
import com.example.AdvocateLink.dto.ManageableDTO;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "client")
@DiscriminatorValue("Client")
public class Client extends Manageable{
    private String oab;

    public Client(ClientDTO dto) {
        super(dto.getId(), dto.getName(), dto.getCpf(),dto.getUrlPhoto(), dto.getRole_id());
        this.oab = dto.getOab();
    }

    public Client(ClientDTO dto, Set<AddressDTO> addressesDTO, Set<ContactDTO> contactsDTO) {
        this(dto);
        addressesDTO.forEach(x-> super.addresses.add(new Address(x,this)));
        contactsDTO.forEach(x-> super.contacts.add(new Contact(x,this)));
    }
}
