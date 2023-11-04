package com.example.AdvocateLink.services;

import com.example.AdvocateLink.dto.ManageableDTO;
import com.example.AdvocateLink.models.Manageable;
import com.example.AdvocateLink.repostories.ManageableRepository;
import com.example.AdvocateLink.services.exceptions.DataBaseException;
import com.example.AdvocateLink.services.exceptions.ResourceNotFoundException;
import com.example.AdvocateLink.services.interfaces.Iservice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.module.ResolutionException;

@Service
public class ManageableService implements Iservice<ManageableDTO> {
    @Autowired
    private ManageableRepository repository;
    final private Logger logger = LoggerFactory.getLogger(ManageableService.class);
    @Override
    @Transactional
    public ManageableDTO insert(ManageableDTO manageableDTO) {
        Manageable entity = repository.save(new Manageable(manageableDTO,manageableDTO.getAddressesDTO(),manageableDTO.getContactsDTO()));
        return new ManageableDTO(entity,entity.getAddresses(),entity.getContacts());
    }
    @Override
    @Transactional
    public void delete(Long id) {
        try{
            Manageable entity = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Id not Found "+id));
            repository.delete(entity);
            logger.info("Delete Id "+id);
        }catch (DataIntegrityViolationException ex){
            throw new DataBaseException("Integrity Violation");
        }
    }
    @Override
    public ManageableDTO update(Long id, ManageableDTO manageableDTO) {
        return null;
    }
    @Override
    @Transactional(readOnly = true)
    public Page<ManageableDTO> list(PageRequest pageRequest) {
        return repository.findAll(pageRequest).map(x-> new ManageableDTO(x,x.getAddresses(),x.getContacts()));
    }
    @Override
    @Transactional(readOnly = true)
    public ManageableDTO findById(Long id) {
        Manageable entity = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Id Not Found "+id));
        logger.info("Manageable found");
        return new ManageableDTO(entity, entity.getAddresses(),entity.getContacts());
    }
}
