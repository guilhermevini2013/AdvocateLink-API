package com.example.AdvocateLink.services;

import com.example.AdvocateLink.dto.EmployeeDTO;
import com.example.AdvocateLink.models.Address;
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

import java.util.Set;

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
        Employee entity = new Employee(employeeDTO, employeeDTO.getAddressesDTO(), employeeDTO.getContactsDTO());
        entity= repository.save(entity);
        logger.info("Employee Inserted");
        return new EmployeeDTO(entity, entity.getAddresses(), entity.getContacts());
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        try {
            Employee entity = repository.findEmployeeById(id).orElseThrow(() -> new ResourceNotFoundException("Id not Found " + id));
            repository.delete(entity);
            logger.info("Delete Id " + id);
        } catch (DataIntegrityViolationException ex) {
            throw new DataBaseException("Integrity Violation");
        }
    }

    @Override
    @Transactional
    public EmployeeDTO update(Long id, EmployeeDTO employeeDTO) {
        try {
            Employee entity = repository.findEmployeeById(id).orElseThrow(() -> new ResourceNotFoundException("Id not Found " + id));
            updateObject(employeeDTO, entity);
            repository.save(entity);
            logger.info("Employee Updated");
            return new EmployeeDTO(entity);
        } catch (EntityNotFoundException | ObjectNotFoundException ex) {
            throw new ResourceNotFoundException("Id Not Found " + id);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EmployeeDTO> list(Pageable pageable) {
        logger.info("Page Listed");
        return repository.findAllEmployees(pageable).map(x -> new EmployeeDTO((Employee) x, x.getAddresses(), x.getContacts()));
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeDTO findById(Long id) {
        Employee entity = repository.findEmployeeById(id).orElseThrow(() -> new ResourceNotFoundException("Id Not Found " + id));
        logger.info("Employee Found");
        return new EmployeeDTO(entity, entity.getAddresses(), entity.getContacts());
    }

    private void updateObject(EmployeeDTO dto, Employee employee) {
        if (dto.getSalary()==0.0|| dto.getName().isEmpty()||dto.getRole_id()==null||dto.getCpf().isEmpty()|| dto.getUrlPhoto().isEmpty()) throw new LackOfInformationException("Attributes Null");
        employee.setName(dto.getName());
        employee.setCpf(dto.getCpf());
        employee.setUrlPhoto(dto.getUrlPhoto());
        employee.setSalary(dto.getSalary());
    }
}
