package com.example.AdvocateLink.services;

import com.example.AdvocateLink.dto.ClientDTO;
import com.example.AdvocateLink.models.Client;
import com.example.AdvocateLink.repostories.ManageableRepository;
import com.example.AdvocateLink.services.interfaces.Iservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClientService implements Iservice<ClientDTO> {
    private ManageableRepository repository;
    @Autowired
    public ClientService(ManageableRepository repository) {
        this.repository = repository;
    }

    @Override
    public ClientDTO insert(ClientDTO dto) {
        Client entity = repository.save(new Client(dto,dto.getAddressesDTO(),dto.getContactsDTO()));
        return new ClientDTO(entity,entity.getAddresses(),entity.getContacts());
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public ClientDTO update(Long id, ClientDTO dto) {
        return null;
    }

    @Override
    public Page<ClientDTO> list(Pageable Pageable) {
        return null;
    }

    @Override
    public ClientDTO findById(Long id) {
        return null;
    }
}
