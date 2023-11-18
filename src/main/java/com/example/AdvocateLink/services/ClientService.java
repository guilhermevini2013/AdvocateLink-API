package com.example.AdvocateLink.services;

import com.example.AdvocateLink.dto.ClientDTO;
import com.example.AdvocateLink.dto.EmployeeDTO;
import com.example.AdvocateLink.models.Client;
import com.example.AdvocateLink.models.Employee;
import com.example.AdvocateLink.repostories.ManageableRepository;
import com.example.AdvocateLink.services.exceptions.DataBaseException;
import com.example.AdvocateLink.services.exceptions.LackOfInformationException;
import com.example.AdvocateLink.services.exceptions.ResourceNotFoundException;
import com.example.AdvocateLink.services.interfaces.Iservice;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.ObjectNotFoundException;
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
            Client entity = repository.findClientById(id).orElseThrow(()-> new ResourceNotFoundException("Id Not Found "+id));
            repository.delete(entity);
            logger.info("Client Deleted");
        }catch (DataIntegrityViolationException ex){
            throw new DataBaseException("Integrity Violation");
        }
    }

    @Override
    @Transactional
    public ClientDTO update(Long id, ClientDTO dto) {
        Client entity = repository.findClientById(id).orElseThrow(() -> new ResourceNotFoundException("Id not Found " + id));
        updateObject(dto, entity);
        repository.save(entity);
        logger.info("Employee Updated");
        return new ClientDTO(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ClientDTO> list(Pageable pageable) {
        logger.info("Page Listed");
        return repository.findAllClient(pageable).map(x-> new ClientDTO(x,x.getAddresses(),x.getContacts()));
    }

    @Override
    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        Client entity = repository.findClientById(id).orElseThrow(()-> new ResourceNotFoundException("Id Not Found "+id));
        logger.info("Client found");
        return new ClientDTO(entity,entity.getAddresses(),entity.getContacts());
    }
    private void updateObject(ClientDTO dto, Client client) {
        if (dto.getOab().isEmpty()|| dto.getName().isEmpty()||dto.getRole_id()==null||dto.getCpf().isEmpty()|| dto.getUrlPhoto().isEmpty()) throw new LackOfInformationException("Attributes Null");
        client.setName(dto.getName());
        client.setCpf(dto.getCpf());
        client.setUrlPhoto(dto.getUrlPhoto());
        client.setOab(dto.getOab());
    }
}
