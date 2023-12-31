package com.example.AdvocateLink.components;

import com.example.AdvocateLink.dto.StatisticsClientDTO;
import com.example.AdvocateLink.dto.StatisticsEmployeeDTO;
import com.example.AdvocateLink.repostories.ManageableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class Statistics {
    private ManageableRepository repository;
    @Autowired
    public Statistics(ManageableRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public StatisticsEmployeeDTO getStatisticsEmployee() {
        return new StatisticsEmployeeDTO(repository.countEmployee(), repository.sumAllSalary());
    }
    @Transactional(readOnly = true)
    public StatisticsClientDTO getStatisticsClient(){
        return new StatisticsClientDTO(repository.countClients());
    }
}
