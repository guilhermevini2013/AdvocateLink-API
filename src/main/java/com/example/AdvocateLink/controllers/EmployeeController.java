package com.example.AdvocateLink.controllers;

import com.example.AdvocateLink.components.Statistics;
import com.example.AdvocateLink.dto.EmployeeDTO;
import com.example.AdvocateLink.dto.StatisticsEmployeeDTO;
import com.example.AdvocateLink.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/advocateLink/api/v1/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService service;
    @Autowired
    private Statistics statistics;

    @GetMapping(value = "/statistics")
    public ResponseEntity<StatisticsEmployeeDTO> statisticsEmployee(){
        return ResponseEntity.ok(statistics.getStatisticsEmployee());
    }

    @GetMapping
    public ResponseEntity<Page<EmployeeDTO>> list(@RequestParam(name = "pages", defaultValue = "0") Integer pages,
                                                  @RequestParam(name = "linesPerPages", defaultValue = "15") Integer linesPerPages,
                                                  @RequestParam(name = "direction", defaultValue = "ASC") String direction,
                                                  @RequestParam(name = "orderBy", defaultValue = "name") String orderBy) {
        return ResponseEntity.ok(service.list(PageRequest.of(pages, linesPerPages, Sort.Direction.valueOf(direction), orderBy)));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EmployeeDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> insert(@RequestBody EmployeeDTO employeeDTO) {
        employeeDTO = service.insert(employeeDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(employeeDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(employeeDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody EmployeeDTO manageableDTO) {
        service.update(id, manageableDTO);
        return ResponseEntity.ok().build();
    }
}
