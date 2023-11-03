package com.example.AdvocateLink.service;

import com.example.AdvocateLink.dto.ManageableDTO;
import com.example.AdvocateLink.services.ManageableService;
import com.example.AdvocateLink.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ManageableServiceTests {
    @Autowired
    private ManageableService manageableService;
    private Long idExists;
    private Long idExists2;
    private Long idNotExists;

    @BeforeEach
    void setUp() {
        idExists = 2l;
        idExists2 = 1l;
        idNotExists = 100l;
    }
    @Test
    public void findByIdShouldReturnObjectWhenIdExists(){
        ManageableDTO entity = manageableService.findById(idExists2);
        Assertions.assertNotNull(entity);
    }
    @Test
    public void findByIdShouldReturnErrorWhenIdNotExists(){
        Assertions.assertThrows(ResourceNotFoundException.class,()->{
            manageableService.findById(idNotExists);
        });
    }
    @Test
    public void deleteShouldDeleteObjectWhenIdExists(){
        manageableService.delete(idExists);
        Assertions.assertThrows(ResourceNotFoundException.class,()->{
            manageableService.findById(idExists);
        });
    }
    @Test
    public void deleteShouldReturnErrorWhenIdNotExists(){
        Assertions.assertThrows(ResourceNotFoundException.class,()->{
            manageableService.delete(idNotExists);
        });
    }
}
