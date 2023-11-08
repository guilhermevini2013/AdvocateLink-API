package com.example.AdvocateLink.service;

import com.example.AdvocateLink.dto.RoleDTO;
import com.example.AdvocateLink.models.Role;
import com.example.AdvocateLink.repostories.RoleRepository;
import com.example.AdvocateLink.service.factory.Factory;
import com.example.AdvocateLink.services.RoleService;
import com.example.AdvocateLink.services.exceptions.DataBaseException;
import com.example.AdvocateLink.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class RoleServiceTests {
    @InjectMocks
    private RoleService roleService;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private RoleDTO roleDTO;
    private Role role;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        roleService = new RoleService(roleRepository);
        roleDTO = Factory.createRoleDto();
        role = Factory.createRole();
    }
    @Test
    public void insertShouldInsertObjectWhenObjectDifferentNull(){
        Mockito.when(roleRepository.save(Mockito.any(Role.class))).thenReturn(role);
        roleDTO = roleService.insert(roleDTO);
        Assertions.assertEquals(2l,roleDTO.getId());
    }
}
