package com.example.AdvocateLink.services;

import com.example.AdvocateLink.dto.RoleDTO;
import com.example.AdvocateLink.models.Role;
import com.example.AdvocateLink.repostories.RoleRepository;
import com.example.AdvocateLink.services.interfaces.Iservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements Iservice<RoleDTO> {
    @Autowired
    private RoleRepository repository;

    @Override
    public RoleDTO insert(RoleDTO roleDTO) {
        Role entity = new Role(roleDTO);
        entity = repository.save(entity);
        return new RoleDTO(entity);
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Boolean update(Long id, RoleDTO roleDTO) {
        return null;
    }

    @Override
    public Page<RoleDTO> list(PageRequest pageRequest) {
        return null;
    }

    @Override
    public RoleDTO findById(Long id) {
        return null;
    }
}
