package com.example.AdvocateLink.service;

import com.example.AdvocateLink.dto.ClientDTO;
import com.example.AdvocateLink.dto.EmployeeDTO;
import com.example.AdvocateLink.models.Client;
import com.example.AdvocateLink.models.Employee;
import com.example.AdvocateLink.repostories.ManageableRepository;
import com.example.AdvocateLink.service.factory.Factory;
import com.example.AdvocateLink.services.ClientService;
import com.example.AdvocateLink.services.EmployeeService;
import com.example.AdvocateLink.services.exceptions.LackOfInformationException;
import com.example.AdvocateLink.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@MockitoSettings(strictness = Strictness.LENIENT)
public class ClientServiceTests {
    @InjectMocks
    private ClientService clientService;
    @Mock
    private ManageableRepository manageableRepository;
    private Long idExists;
    private Long idNotExists;
    private ClientDTO clientDTO;
    private Client client;
    private Page<Client> clientPage;
    @Captor
    private ArgumentCaptor<Client> clientCaptor;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        idExists = 1l;
        idNotExists = 2l;
        clientDTO = Factory.createClientDto();
        client = Factory.createClient();
        clientPage = new PageImpl<>(List.of(client,client));
        clientService = new ClientService(manageableRepository);
        when(manageableRepository.save((Client)any())).thenReturn(client);
        when(manageableRepository.findClientById(idExists)).thenReturn(Optional.of(client));
        doNothing().when(manageableRepository).delete(any());
        when(manageableRepository.findClientById(idNotExists)).thenReturn(Optional.empty());
        when(manageableRepository.findAllClient(any())).thenReturn(clientPage);
        doThrow(ResourceNotFoundException.class).when(manageableRepository).findEmployeeById(idNotExists);
    }
    @Test
    public void updateShouldThrowLackOfInformationExceptionAndNotSaveInDataBaseWhenHaveAttributesNull(){
        clientDTO.setName("");
        assertThrows(LackOfInformationException.class,()-> clientService.update(idExists, clientDTO));
        verify(manageableRepository,never()).save(any());
    }
    @Test
    public void updateShouldThrowResourceNotFoundExceptionAndNotSaveInDataBaseWhenIdNotExists(){
        assertThrows(ResourceNotFoundException.class,()-> clientService.update(idNotExists, clientDTO));
        verify(manageableRepository,never()).save(any());
    }
    @Test
    public void updateShouldUpdatedObjectAndSaveInDatabaseWhenIdExists(){
        clientDTO.setName("Hello, word");
        assertDoesNotThrow(()-> clientService.update(idExists, clientDTO));
        verify(manageableRepository,times(1)).save(clientCaptor.capture());
        client = clientCaptor.getValue();
        assertEquals(clientDTO.getName(),client.getName());
    }
    @Test
    public void findByIdShouldThrowResourceNotFoundExceptionAndNotReturnObjectWhenIdNotExists(){
        assertThrows(ResourceNotFoundException.class,()-> clientService.findById(idNotExists));
        verify(manageableRepository,times(1)).findClientById(idNotExists);
    }
    @Test
    public void findByIdShouldReturnObjectDTOWhenIdExists(){
        Assertions.assertDoesNotThrow(()-> clientService.findById(idExists));
        Assertions.assertNotNull(clientService.findById(idExists));
        verify(manageableRepository,times(2)).findClientById(idExists);

    }
    @Test
    public void listShouldListAllEmployee(){
        Pageable pageable = PageRequest.of(0, 10);
        assertNotNull(clientService.list(pageable));
        verify(manageableRepository, times(1)).findAllClient(pageable);
    }
    @Test
    public void deleteByIdShouldThrowResourceNotFoundExceptionAndNotSaveInDataBaseWhenIdNotExists(){
        assertThrows(ResourceNotFoundException.class,()-> clientService.deleteById(idNotExists));
        verify(manageableRepository,times(1)).findClientById(idNotExists);
        verify(manageableRepository,never()).delete(any());
    }
    @Test
    public void deleteByIdShouldRemoveObjectInDataBaseWhenIdExists(){
        clientService.deleteById(idExists);
        verify(manageableRepository,times(1)).findClientById(idExists);
        verify(manageableRepository,times(1)).delete(any());
    }
    @Test
    public void insertShouldInsertInDataBaseAndReturnObjectDTO(){
        clientDTO = clientService.insert(clientDTO);
        verify(manageableRepository,times(1)).save(any());
        assertNotNull(clientDTO.getId());
    }
}
