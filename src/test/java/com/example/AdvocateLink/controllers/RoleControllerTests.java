package com.example.AdvocateLink.controllers;

import com.example.AdvocateLink.dto.RoleDTO;
import com.example.AdvocateLink.models.Role;
import com.example.AdvocateLink.service.factory.Factory;
import com.example.AdvocateLink.services.RoleService;
import com.example.AdvocateLink.services.exceptions.LackOfInformationException;
import com.example.AdvocateLink.services.exceptions.ResourceNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.List;
import static org.hamcrest.Matchers.is;

@WebMvcTest(RoleController.class)
public class RoleControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RoleService service;
    private Pageable pageable;
    private Page<RoleDTO> roleDTOS;
    private Role role;
    private RoleDTO dto;
    private RoleDTO dtonull;
    private Long idExists;
    private Long idNotExists;
    @BeforeEach
    void setUp() {
        idExists = 1l;
        idNotExists = 2l;
        dto = Factory.createRoleDto();
        roleDTOS = new PageImpl<>(List.of(dto,dto));
        pageable = PageRequest.of(0,1);
        dtonull = Factory.createRoleDtoNullAttributes();
        when(service.list(any())).thenReturn(roleDTOS);
        doNothing().when(service).deleteById(idExists);
        doThrow(ResourceNotFoundException.class).when(service).deleteById(idNotExists);
        when(service.insert(any())).thenReturn(dto);
        when(service.update(eq(idExists),any(RoleDTO.class))).thenReturn(dto);
        doThrow(ResourceNotFoundException.class).when(service).update(eq(idNotExists),any(RoleDTO.class));
    }
    @Test
    public void updateShouldThrowLackOfInformationExceptionAndNotSaveInDataBaseWhenIfHaveAttributesNull() throws Exception {
        doThrow(LackOfInformationException.class).when(service).update(eq(idExists),any(RoleDTO.class));
        mockMvc.perform(put("/advocateLink/api/v1/role/{id}",idExists)
                .content(asJsonString(dtonull))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn().getResponse();
        verify(service,times(1)).update(eq(idExists),any(RoleDTO.class));
    }
    @Test
    public void updateShouldThrowResourceNotFoundExceptionAndNotSaveDataBaseWhenIdNotExists() throws Exception {
        mockMvc.perform(put("/advocateLink/api/v1/role/{id}",idNotExists)
                .content(asJsonString(dto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn().getResponse();
        assertThrows(ResourceNotFoundException.class,()-> service.update(idNotExists,dto));
        verify(service,times(1)).update(idNotExists,dto);
    }
    @Test
    public void updateShouldUpdatedObjectAndSaveInDataBaseWhenIExistsAndAttributesNotIsNull() throws Exception {
        mockMvc.perform(put("/advocateLink/api/v1/role/{id}",idExists)
                .content(asJsonString(dto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        assertDoesNotThrow(()-> service.update(idExists,dto));
        verify(service,times(1)).update(idExists,dto);
    }
    @Test
    public void insertShouldSaveInDataBaseAndReturnObjectSaved() throws Exception {
        mockMvc.perform(post("/advocateLink/api/v1/role")
                .content(asJsonString(roleDTOS))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn().getResponse();
        verify(service,times(1)).insert(any());
        verifyNoMoreInteractions(service);
        assertDoesNotThrow(()-> service.insert(dto));
    }
    @Test
    public void deleteShouldThrowResourceNotFoundExceptionAndNothingDeleteObjectInDataBaseWhenIdNotExists() throws Exception {
        mockMvc.perform(delete("/advocateLink/api/v1/role/{id}",idNotExists))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status",is(HttpStatus.NOT_FOUND.value())));
        verify(service,times(1)).deleteById(idNotExists);
        verifyNoMoreInteractions(service);
        assertThrows(ResourceNotFoundException.class,()-> service.deleteById(idNotExists));
    }
    @Test
    public void deleteShouldDeleteObjectInDataBaseAndHTTPStatusEqualsNoContentWhenIdExists() throws Exception {
        mockMvc.perform(delete("/advocateLink/api/v1/role/{id}",idExists))
                .andExpect(status().isNoContent());
        verify(service,times(1)).deleteById(idExists);
        verifyNoMoreInteractions(service);
        assertDoesNotThrow(()-> service.deleteById(idExists));
    }
    @Test
    public void listShouldReturnPageAndHTTPStatusEqualsIsOk() throws Exception {
        mockMvc.perform(get("/advocateLink/api/v1/role")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[0].name_Role", is(dto.getName_Role())))
                .andExpect(jsonPath("$.content[1].name_Role", is(dto.getName_Role())));
        verify(service,times(1)).list(any());
        verifyNoMoreInteractions(service);
        assertDoesNotThrow(()-> service.list(pageable));
    }
    private String asJsonString(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
