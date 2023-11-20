package com.example.AdvocateLink.controllers;

import com.example.AdvocateLink.dto.RoleDTO;
import com.example.AdvocateLink.models.Employee;
import com.example.AdvocateLink.models.Role;
import com.example.AdvocateLink.service.factory.Factory;
import com.example.AdvocateLink.services.RoleService;
import com.example.AdvocateLink.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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
import org.springframework.test.web.servlet.ResultMatcher;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    private Long idExists;
    private Long idNotExists;
    @BeforeEach
    void setUp() {
        idExists = 1l;
        idNotExists = 2l;
        dto = Factory.createRoleDto();
        roleDTOS = new PageImpl<>(List.of(dto,dto));
        pageable = PageRequest.of(0,1);
        when(service.list(any())).thenReturn(roleDTOS);
        doNothing().when(service).deleteById(idExists);
        doThrow(ResourceNotFoundException.class).when(service).deleteById(idNotExists);
    }
    @Test
    public void deleteShouldThrowResourceNotFoundExceptionAndNothingDeleteObjectInDataBaseWhenIdNotExists() throws Exception {
        mockMvc.perform(delete("/advocateLink/api/v1/role/{id}",idNotExists))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status",is(HttpStatus.NOT_FOUND.value())));
        verify(service,times(1)).deleteById(idNotExists);
    }
    @Test
    public void deleteShouldDeleteObjectInDataBaseAndHTTPStatusEqualsNoContentWhenIdExists() throws Exception {
        mockMvc.perform(delete("/advocateLink/api/v1/role/{id}",idExists))
                .andExpect(status().isNoContent());
        verify(service,times(1)).deleteById(idExists);
    }
    @Test
    public void listShouldReturnPageAndHTTPStatusEqualsIsOk() throws Exception {
        mockMvc.perform(get("/advocateLink/api/v1/role")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[0].name_Role", is(dto.getName_Role())))
                .andExpect(jsonPath("$.content[1].name_Role", is(dto.getName_Role())));
        verify(service,times(1)).list(any());
    }
}
