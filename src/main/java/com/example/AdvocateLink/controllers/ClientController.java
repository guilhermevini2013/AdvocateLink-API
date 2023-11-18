package com.example.AdvocateLink.controllers;

import com.example.AdvocateLink.components.Statistics;
import com.example.AdvocateLink.dto.ClientDTO;
import com.example.AdvocateLink.dto.EmployeeDTO;
import com.example.AdvocateLink.dto.StatisticsClientDTO;
import com.example.AdvocateLink.dto.StatisticsEmployeeDTO;
import com.example.AdvocateLink.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/advocateLink/api/v1/client")
public class ClientController {
    @Autowired
    private ClientService clientService;
    @Autowired
    private Statistics statistics;
    @GetMapping(value = "/statistics")
    public ResponseEntity<StatisticsClientDTO> statisticsEmployee(){
        return ResponseEntity.ok(statistics.getStatisticsClient());
    }
    @GetMapping
    public ResponseEntity<Page<ClientDTO>> list(@RequestParam(name = "page",defaultValue = "0")Integer page,
                                                @RequestParam(name = "linesPerPage",defaultValue = "15")Integer linesPerPage,
                                                @RequestParam(name = "direction",defaultValue = "ASC")String direction,
                                                @RequestParam(name = "orderBy",defaultValue = "name")String orderBy){
        return ResponseEntity.ok(clientService.list(PageRequest.of(page,linesPerPage, Sort.Direction.valueOf(direction),orderBy)));
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> findByid(@PathVariable Long id){
        return ResponseEntity.ok(clientService.findById(id));
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        clientService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping
    public ResponseEntity<ClientDTO> insert(@RequestBody ClientDTO clientDTO) {
        clientDTO = clientService.insert(clientDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clientDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(clientDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
        clientService.update(id, clientDTO);
        return ResponseEntity.ok().build();
    }
}
