package com.example.AdvocateLink.service;

import com.example.AdvocateLink.dto.RoleDTO;
import com.example.AdvocateLink.service.factory.Factory;
import com.example.AdvocateLink.services.RoleService;
import com.example.AdvocateLink.services.exceptions.DataBaseException;
import com.example.AdvocateLink.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class RoleServiceTests {
    @Autowired
    private RoleService roleService;
    private RoleDTO roleDTO;
    private Long idExists;
    private Long idNotExists;
    @BeforeEach
    void setUp() {
        roleDTO = Factory.createRoleDto();
        idExists = 1l;
        idNotExists = 100l;
    }

    @Test
    public void insertShouldInsertIntoBD() {
        roleDTO = roleService.insert(roleDTO);
        assertTrue(2l == roleDTO.getId());
    }

    @Test
    public void deleteShouldReturnDataBaseExceptionWhenIntegrityViolation(){
        assertThrows(DataBaseException.class,()-> roleService.deleteById(idExists));
    }
    @Test void deleteShouldReturnResourceNotFoundExceptionWhenIdNotExists(){
        assertThrows(ResourceNotFoundException.class,()-> roleService.deleteById(idNotExists));
    }
}
