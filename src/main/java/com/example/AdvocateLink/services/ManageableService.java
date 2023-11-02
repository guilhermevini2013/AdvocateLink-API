package com.example.AdvocateLink.services;

import com.example.AdvocateLink.dto.ManageableDTO;
import com.example.AdvocateLink.models.Manageable;
import com.example.AdvocateLink.repostories.ManageableRepository;
import com.example.AdvocateLink.services.exceptions.ResourceNotFoundException;
import com.example.AdvocateLink.services.interfaces.Iservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.module.ResolutionException;

@Service
public class ManageableService implements Iservice<ManageableDTO> {
    @Autowired
    private ManageableRepository repository;
    @Override
    public ManageableDTO insert(ManageableDTO manageableDTO) {
        return null;
    }

    @Override
    public Void delete(Long id) {
        return null;
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
        return new ManageableDTO(entity, entity.getAddresses(),entity.getContacts());
    }
}
