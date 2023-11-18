package com.example.AdvocateLink.services;

import com.example.AdvocateLink.dto.RoleDTO;
import com.example.AdvocateLink.models.Role;
import com.example.AdvocateLink.repostories.RoleRepository;
import com.example.AdvocateLink.services.exceptions.DataBaseException;
import com.example.AdvocateLink.services.exceptions.LackOfInformationException;
import com.example.AdvocateLink.services.exceptions.ResourceNotFoundException;
import com.example.AdvocateLink.services.interfaces.Iservice;
import jakarta.persistence.EntityNotFoundException;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

@Service
public class RoleService implements Iservice<RoleDTO> {
    final private Logger logger = LoggerFactory.getLogger(RoleService.class);

    private RoleRepository repository;

    @Autowired
    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public RoleDTO insert(RoleDTO roleDTO) {
        Role entity = new Role(roleDTO);
        entity = repository.save(entity);
        logger.info(entity.getId()+" insert success");
        return new RoleDTO(entity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        try {
            Role entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id Not Found " + id));
            repository.delete(entity);
            logger.info(id+" deleted success");
        } catch (DataIntegrityViolationException ex) {
            throw new DataBaseException("Role Used");
        }
    }

    @Override
    @Transactional
    public RoleDTO update(Long id, RoleDTO roleDTO) {
        try {
            Role entity = repository.getReferenceById(id);
            alterRole(roleDTO, entity);
            logger.info(id+" updated success");
            return new RoleDTO(repository.save(entity));
        } catch (EntityNotFoundException ex) {
            throw new ResourceNotFoundException("Id Not Found " + id);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RoleDTO> list(Pageable pageable) {
        logger.info("Listed success");
        return repository.findAll(pageable).map(RoleDTO::new);
    }

    @Override
    @Transactional(readOnly = true)
    public RoleDTO findById(Long id) {
        return null;
    }

    private void alterRole(RoleDTO roleDTO, Role role) {
        if (roleDTO.getName_Role() == null) throw new LackOfInformationException("Attributes Null");
        role.setName_Role(roleDTO.getName_Role());
    }
}
