package com.example.AdvocateLink.services;

import com.example.AdvocateLink.dto.RoleDTO;
import com.example.AdvocateLink.models.Role;
import com.example.AdvocateLink.repostories.RoleRepository;
import com.example.AdvocateLink.services.exceptions.DataBaseException;
import com.example.AdvocateLink.services.exceptions.LackOfInformationException;
import com.example.AdvocateLink.services.exceptions.ResourceNotFoundException;
import com.example.AdvocateLink.services.interfaces.Iservice;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements Iservice<RoleDTO> {

    private RoleRepository repository;
    @Autowired
    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public RoleDTO insert(RoleDTO roleDTO) {
        Role entity = new Role(roleDTO);
        entity = repository.save(entity);
        return new RoleDTO(entity);
    }

    @Override
    public void deleteById(Long id) {
        try{
            Role entity = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Id Not Found " +id));
            repository.delete(entity);
        }catch (DataIntegrityViolationException ex){
            throw new DataBaseException("Role Used: "+ex.getMessage());
        }
    }

    @Override
    public RoleDTO update(Long id, RoleDTO roleDTO) {
        try{
            Role entity = repository.getReferenceById(id);
            alterRole(roleDTO,entity);
            return new RoleDTO(repository.save(entity));
        }catch (EntityNotFoundException ex){
            throw new ResourceNotFoundException("Id Not Found "+id);
        }
    }

    @Override
    public Page<RoleDTO> list(PageRequest pageRequest) {
        return null;
    }

    @Override
    public RoleDTO findById(Long id) {
        return null;
    }
    private void alterRole(RoleDTO roleDTO,Role role){
        if (roleDTO.getNameRole()==null) throw new LackOfInformationException("Attributes Null");
        role.setNameRole(roleDTO.getNameRole());
    }
}
