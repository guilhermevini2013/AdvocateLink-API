package com.example.AdvocateLink.components;

import com.example.AdvocateLink.dto.StatisticsEmployeeDTO;
import com.example.AdvocateLink.repostories.ManageableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class StatisticsOfEmployee {
    private ManageableRepository repository;
    @Autowired
    public StatisticsOfEmployee(ManageableRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public StatisticsEmployeeDTO statistics() {
        return new StatisticsEmployeeDTO(repository.countEmployee(), repository.sumAllSalary());
    }
}
