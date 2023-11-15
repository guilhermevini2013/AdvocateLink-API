package com.example.AdvocateLink.controllers;

import com.example.AdvocateLink.dto.ClientDTO;
import com.example.AdvocateLink.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/advocateLink/api/v1/client")
public class ClientController {
    @Autowired
    private ClientService clientService;
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
}
