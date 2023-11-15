package com.example.AdvocateLink.dto;

import com.example.AdvocateLink.models.Address;
import com.example.AdvocateLink.models.Client;
import com.example.AdvocateLink.models.Contact;
import com.example.AdvocateLink.models.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
public class ClientDTO extends ManageableDTO{
    private String oab;

    public ClientDTO(Client client) {
        super(client.getId(), client.getName(), client.getCpf(), client.getUrlPhoto(), client.getRole_Id());
        this.oab = client.getOab();
    }

    public ClientDTO(Client entity, Set<Address> addresses, Set<Contact> contacts) {
        this(entity);
        addresses.forEach(x-> super.addressesDTO.add(new AddressDTO(x)));
        contacts.forEach(x-> super.contactsDTO.add(new ContactDTO(x)));
    }
}
