package com.example.AdvocateLink.services;

import com.example.AdvocateLink.dto.EmployeeDTO;
import com.example.AdvocateLink.models.Employee;
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
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService implements Iservice<EmployeeDTO> {
    final private Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    private ManageableRepository repository;
    @Autowired
    public EmployeeService(ManageableRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public EmployeeDTO insert(EmployeeDTO employeeDTO) {
        Employee entity = repository.save(new Employee(employeeDTO, employeeDTO.getAddressesDTO(), employeeDTO.getContactsDTO()));
        logger.info("Manageable Insert");
        return new EmployeeDTO(entity, entity.getAddresses(), entity.getContacts());
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        try {
            Employee entity = (Employee) repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id not Found " + id));
            repository.delete(entity);
            logger.info("Delete Id " + id);
        } catch (DataIntegrityViolationException ex) {
            throw new DataBaseException("Integrity Violation");
        }
    }

    @Override
    @Transactional
    public EmployeeDTO update(Long id, EmployeeDTO manageableDTO) {
        try {
            Employee entity = (Employee) repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id not Found " + id));
            updateObject(manageableDTO, entity);
            repository.save(entity);
            logger.info("Manageable Updated");
            return new EmployeeDTO(entity);
        } catch (EntityNotFoundException | ObjectNotFoundException ex) {
            throw new ResourceNotFoundException("Id Not Found " + id);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EmployeeDTO> list(Pageable pageable) {
        logger.info("Page<ManageableDTO> Listed");
        return repository.findAllEmployees(pageable).map(x -> new EmployeeDTO((Employee) x, x.getAddresses(), x.getContacts()));
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeDTO findById(Long id) {
        Employee entity = (Employee) repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id Not Found " + id));
        logger.info("Manageable Found");
        return new EmployeeDTO(entity, entity.getAddresses(), entity.getContacts());
    }

    private void updateObject(EmployeeDTO dto, Employee manageable) {
        manageable.setName(dto.getName());
        manageable.setCpf(dto.getCpf());
        manageable.setUrlPhoto(dto.getUrlPhoto());
        manageable.setSalary(dto.getSalary());
    }
}
