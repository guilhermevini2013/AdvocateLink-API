package com.example.AdvocateLink.services;

import com.example.AdvocateLink.dto.ClientDTO;
import com.example.AdvocateLink.models.Client;
import com.example.AdvocateLink.repostories.ManageableRepository;
import com.example.AdvocateLink.services.exceptions.DataBaseException;
import com.example.AdvocateLink.services.exceptions.ResourceNotFoundException;
import com.example.AdvocateLink.services.interfaces.Iservice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService implements Iservice<ClientDTO> {
    private ManageableRepository repository;
    final private Logger logger = LoggerFactory.getLogger(ClientService.class);
    @Autowired
    public ClientService(ManageableRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public ClientDTO insert(ClientDTO dto) {
        Client entity = repository.save(new Client(dto,dto.getAddressesDTO(),dto.getContactsDTO()));
        logger.info("Client Inserted");
        return new ClientDTO(entity,entity.getAddresses(),entity.getContacts());
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        try{
            Client entity = (Client) repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Id Not Found "+id));
            repository.delete(entity);
        }catch (DataIntegrityViolationException ex){
            throw new DataBaseException("Integrity Violation");
        }
    }

    @Override
    @Transactional
    public ClientDTO update(Long id, ClientDTO dto) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ClientDTO> list(Pageable pageable) {
        return repository.findAllClient(pageable).map(x-> new ClientDTO(x,x.getAddresses(),x.getContacts()));
    }

    @Override
    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        return null;
    }
}
