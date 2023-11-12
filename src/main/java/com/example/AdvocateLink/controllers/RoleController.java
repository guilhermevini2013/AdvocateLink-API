package com.example.AdvocateLink.controllers;

import com.example.AdvocateLink.dto.ManageableDTO;
import com.example.AdvocateLink.dto.RoleDTO;
import com.example.AdvocateLink.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/advocateLink/api/v1/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @GetMapping
    public ResponseEntity<Page<RoleDTO>> list(@RequestParam(name = "pages", defaultValue = "0") Integer pages,
                                              @RequestParam(name = "linesPerPages", defaultValue = "15") Integer linesPerPages,
                                              @RequestParam(name = "direction", defaultValue = "ASC") String direction,
                                              @RequestParam(name = "orderBy", defaultValue = "id") String orderBy) {
        return ResponseEntity.ok(roleService.list(PageRequest.of(pages, linesPerPages, Sort.Direction.valueOf(direction), orderBy)));
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        roleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<RoleDTO> update(@PathVariable Long id,@RequestBody RoleDTO dto){
        return ResponseEntity.ok(roleService.update(id,dto));
    }
    @PostMapping
    public ResponseEntity<RoleDTO> insert(@RequestBody RoleDTO dto){
        dto = roleService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
}
