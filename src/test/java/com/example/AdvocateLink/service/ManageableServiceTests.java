package com.example.AdvocateLink.service;

import com.example.AdvocateLink.dto.ManageableDTO;
import com.example.AdvocateLink.service.factory.Factory;
import com.example.AdvocateLink.services.ManageableService;
import com.example.AdvocateLink.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ManageableServiceTests {
    @Autowired
    private ManageableService manageableService;
    private Long idExists;
    private Long idExists2;
    private Long idNotExists;
    private ManageableDTO manageableDTO;

    @BeforeEach
    void setUp() {
        idExists = 2l;
        idExists2 = 1l;
        idNotExists = 100l;
        manageableDTO = Factory.createManageableDTO();
    }

    @Test
    public void findByIdShouldReturnObjectWhenIdExists() {
        ManageableDTO entity = manageableService.findById(idExists2);
        assertNotNull(entity);
    }

    @Test
    public void findByIdShouldReturnResourceNotFoundExceptionWhenIdNotExists() {
        assertThrows(ResourceNotFoundException.class, () -> manageableService.findById(idNotExists));
    }

    @Test
    public void deleteShouldDeleteObjectWhenIdExists() {
        manageableService.deleteById(idExists);
        assertThrows(ResourceNotFoundException.class, () -> manageableService.findById(idExists));
    }

    @Test
    public void deleteShouldReturnResourceNotFoundExceptionWhenIdNotExists() {
        assertThrows(ResourceNotFoundException.class, () -> manageableService.deleteById(idNotExists));
    }

    @Test
    public void insertShouldInsertIntoBD() {
        manageableDTO = manageableService.insert(manageableDTO);
        assertTrue(3l == manageableDTO.getId());
    }

    @Test
    public void updateShouldUpdateObjectWhenIdExists() {
        assertTrue(manageableService.update(idExists, manageableDTO));
    }

    @Test
    public void updateShouldReturnResourceNotFoundExceptionWhenIdNotExists() {
        assertThrows(ResourceNotFoundException.class, () -> manageableService.update(idNotExists, manageableDTO));
    }
}
