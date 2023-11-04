package com.example.AdvocateLink.controllers;

import com.example.AdvocateLink.dto.ManageableDTO;
import com.example.AdvocateLink.services.ManageableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/advocateLink/api/v1/manageable")
public class ManageableController {
    @Autowired
    private ManageableService service;
    @GetMapping
    public ResponseEntity<Page<ManageableDTO>> list(@RequestParam(name = "pages",defaultValue = "0")Integer pages,
                                                    @RequestParam(name = "linesPerPages",defaultValue = "15")Integer linesPerPages,
                                                    @RequestParam(name = "direction",defaultValue = "ASC")String direction,
                                                    @RequestParam(name = "orderBy",defaultValue = "name")String orderBy){
        return ResponseEntity.ok(service.list(PageRequest.of(pages,linesPerPages, Sort.Direction.valueOf(direction),orderBy)));
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<ManageableDTO> findByid(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
