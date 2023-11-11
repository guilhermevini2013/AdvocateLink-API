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

}
