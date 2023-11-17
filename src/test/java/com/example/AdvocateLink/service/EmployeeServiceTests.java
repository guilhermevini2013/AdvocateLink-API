package com.example.AdvocateLink.service;

import com.example.AdvocateLink.dto.EmployeeDTO;
import com.example.AdvocateLink.models.Employee;
import com.example.AdvocateLink.repostories.ManageableRepository;
import com.example.AdvocateLink.service.factory.Factory;
import com.example.AdvocateLink.services.EmployeeService;
import com.example.AdvocateLink.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@MockitoSettings(strictness = Strictness.LENIENT)
public class EmployeeServiceTests {
    @InjectMocks
    private EmployeeService manageableService;
    @Mock
    private ManageableRepository manageableRepository;
    private Long idExists;
    private Long idNotExists;
    private EmployeeDTO employeeDTO;
    private Employee employee;
    private Page<Employee>  employeePage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        idExists = 1l;
        idNotExists = 2l;
        employeeDTO = Factory.createEmployeeDTO();
        employee = Factory.createEmployee();
        employeePage = new PageImpl<>(List.of(employee,employee));
        manageableService = new EmployeeService(manageableRepository);
        when(manageableRepository.save((Employee)any())).thenReturn(employee);
        when(manageableRepository.findEmployeeById(idExists)).thenReturn(Optional.of(employee));
        doNothing().when(manageableRepository).delete(any());
        when(manageableRepository.findEmployeeById(idNotExists)).thenReturn(Optional.empty());
        when(manageableRepository.findAllEmployees(any())).thenReturn(employeePage);
        doThrow(ResourceNotFoundException.class).when(manageableRepository).findEmployeeById(idNotExists);
    }
    @Test
    public void updateShouldUpdatedObjectAndSaveInDatabaseWhenIdExists(){

    }
    @Test
    public void findByIdShouldThrowResourceNotFoundExceptionAndNotReturnObjectWhenIdNotExists(){
        assertThrows(ResourceNotFoundException.class,()-> manageableService.findById(idNotExists));
        verify(manageableRepository,times(1)).findEmployeeById(idNotExists);
    }
    @Test
    public void findByIdShouldReturnObjectDTOWhenIdExists(){
        Assertions.assertDoesNotThrow(()-> manageableService.findById(idExists));
        Assertions.assertNotNull(manageableService.findById(idExists));
        verify(manageableRepository,times(2)).findEmployeeById(idExists);

    }
    @Test
    public void listShouldListAllEmployee(){
        Pageable pageable = PageRequest.of(0, 10);
        assertNotNull(manageableService.list(pageable));
        verify(manageableRepository, times(1)).findAllEmployees(pageable);
    }
    @Test
    public void deleteByIdShouldThrowResourceNotFoundExceptionAndNotSaveInDataBaseWhenIdNotExists(){
        assertThrows(ResourceNotFoundException.class,()->manageableService.deleteById(idNotExists));
        verify(manageableRepository,times(1)).findEmployeeById(idNotExists);
        verify(manageableRepository,never()).delete(any());
    }
    @Test
    public void deleteByIdShouldRemoveObjectInDataBaseWhenIdExists(){
        manageableService.deleteById(idExists);
        verify(manageableRepository,times(1)).findEmployeeById(idExists);
        verify(manageableRepository,times(1)).delete(any());
    }
    @Test
    public void insertShouldInsertInDataBaseAndReturnObjectDTO(){
        employeeDTO = manageableService.insert(employeeDTO);
        verify(manageableRepository,times(1)).save(any());
        assertNotNull(employeeDTO.getId());
    }
}
