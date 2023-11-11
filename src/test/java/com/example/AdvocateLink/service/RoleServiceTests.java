package com.example.AdvocateLink.service;

import com.example.AdvocateLink.dto.RoleDTO;
import com.example.AdvocateLink.models.Role;
import com.example.AdvocateLink.repostories.RoleRepository;
import com.example.AdvocateLink.service.factory.Factory;
import com.example.AdvocateLink.services.RoleService;
import com.example.AdvocateLink.services.exceptions.LackOfInformationException;
import com.example.AdvocateLink.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@MockitoSettings(strictness = Strictness.LENIENT)
public class RoleServiceTests {
    @InjectMocks
    private RoleService roleService;
    @Mock
    private RoleRepository roleRepository;
    private RoleDTO roleDTO;
    private Role role;
    private Long idExists;
    private Long idNotExists;
    @Captor
    private ArgumentCaptor<Role> roleCaptor;
    private Page<Role> rolePage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        roleService = new RoleService(roleRepository);
        roleDTO = Factory.createRoleDto();
        role = Factory.createRole();
        idExists = 1l;
        idNotExists = 2l;
        rolePage = new PageImpl<>(List.of(role));
        when(roleRepository.save(any())).thenReturn(role);
        when(roleRepository.findById(idExists)).thenReturn(Optional.of(role));
        doNothing().when(roleRepository).delete(any());
        doThrow(ResourceNotFoundException.class).when(roleRepository).findById(idNotExists);
        when(roleRepository.getReferenceById(idExists)).thenReturn(role);
        doThrow(EntityNotFoundException.class).when(roleRepository).getReferenceById(idNotExists);
        when(roleRepository.findAll((Pageable) any())).thenReturn(rolePage);
    }

    @Test
    public void listShouldReturnPage() {
        Pageable pageable = PageRequest.of(0, 10);
        assertNotNull(roleService.list(pageable));
        verify(roleRepository, times(1)).findAll(pageable);
    }

    @Test
    public void updateShouldThrowLackOfInformationExceptionAndNotAlterWhenAttributesIsNull() {
        roleDTO = Factory.createRoleDtoNullAttributes();
        assertThrows(LackOfInformationException.class, () -> roleService.update(idExists, roleDTO));
        verify(roleRepository, never()).save(any());
    }

    @Test
    public void updateShouldThrowResourceNotFoundExceptionAndNotAlterWhenIdNotExists() {
        roleDTO.setNameRole("Hellow Word");
        assertThrows(ResourceNotFoundException.class, () -> roleService.update(idNotExists, roleDTO));
        verify(roleRepository, never()).save(any());
    }

    @Test
    public void updateShouldUpdateObjectAndSaveInDataBaseWhenIdExistsAndAllAttributesNotIsNull() {
        roleDTO.setNameRole("Hellow Word");
        assertDoesNotThrow(() -> roleService.update(idExists, roleDTO));
        verify(roleRepository, times(1)).getReferenceById(idExists);
        verify(roleRepository, times(1)).save(roleCaptor.capture());
        Role roleCaptured = roleCaptor.getValue();
        assertEquals(roleCaptured.getNameRole(), roleDTO.getNameRole());
    }

    @Test
    public void deleteByIdShouldReturnResourceNotFoundExceptionAndNotRemoveObjectInDataBaseWhenIdNotExists() {
        assertThrows(ResourceNotFoundException.class, () -> roleService.deleteById(idNotExists));
        verify(roleRepository, times(1)).findById(idNotExists);
        verify(roleRepository, never()).delete(any());
    }

    @Test
    public void deleteByIdShouldDeleteObjectInDateBaseAndNotReturnNeverExceptionWhenIdExists() {
        assertDoesNotThrow(() -> roleService.deleteById(idExists));
        verify(roleRepository, times(1)).findById(idExists);
        verify(roleRepository, times(1)).delete(any());
    }

    @Test
    public void insertShouldInsertObjectInDataBase() {
        roleDTO = roleService.insert(roleDTO);
        Assertions.assertEquals(roleDTO.getId(), role.getId());
        verify(roleRepository, times(1)).save(any());
    }
}
