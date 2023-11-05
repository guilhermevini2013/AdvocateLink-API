package com.example.AdvocateLink.services;

import com.example.AdvocateLink.dto.AddressDTO;
import com.example.AdvocateLink.dto.ManageableDTO;
import com.example.AdvocateLink.models.Address;
import com.example.AdvocateLink.models.Manageable;
import com.example.AdvocateLink.repostories.ManageableRepository;
import com.example.AdvocateLink.services.exceptions.DataBaseException;
import com.example.AdvocateLink.services.exceptions.ResourceNotFoundException;
import com.example.AdvocateLink.services.interfaces.Iservice;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ManageableService implements Iservice<ManageableDTO> {
    final private Logger logger = LoggerFactory.getLogger(ManageableService.class);
    @Autowired
    private ManageableRepository repository;

    @Override
    @Transactional
    public ManageableDTO insert(ManageableDTO manageableDTO) {
        Manageable entity = repository.save(new Manageable(manageableDTO, manageableDTO.getAddressesDTO(), manageableDTO.getContactsDTO()));
        logger.info("ManageableDTO Insert");
        return new ManageableDTO(entity, entity.getAddresses(), entity.getContacts());
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        try {
            Manageable entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id not Found " + id));
            repository.delete(entity);
            logger.info("Delete Id " + id);
        } catch (DataIntegrityViolationException ex) {
            throw new DataBaseException("Integrity Violation");
        }
    }

    @Override
    @Transactional
    public Boolean update(Long id, ManageableDTO manageableDTO) {
        try {
            Manageable entityReference = repository.getReferenceById(id);
            updateObject(manageableDTO, entityReference);
            repository.save(entityReference);
            logger.info("Manageable Updated");
            return true;
        } catch (EntityNotFoundException | ObjectNotFoundException ex) {
            throw new ResourceNotFoundException("Id Not Found " + id);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ManageableDTO> list(PageRequest pageRequest) {
        logger.info("Page<ManageableDTO> Listed");
        return repository.findAll(pageRequest).map(x -> new ManageableDTO(x, x.getAddresses(), x.getContacts()));
    }

    @Override
    @Transactional(readOnly = true)
    public ManageableDTO findById(Long id) {
        Manageable entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id Not Found " + id));
        logger.info("Manageable Found");
        return new ManageableDTO(entity, entity.getAddresses(), entity.getContacts());
    }

    private void updateObject(ManageableDTO dto, Manageable manageable) {
        manageable.setName(dto.getName());
        manageable.setRole_Id(dto.getRole_id());
        manageable.setCpf(dto.getCpf());
        manageable.setSalary(dto.getSalary());
        manageable.setUrlPhoto(dto.getUrlPhoto());
    }
}
