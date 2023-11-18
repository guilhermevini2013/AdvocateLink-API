package com.example.AdvocateLink.repostories;

import com.example.AdvocateLink.models.Client;
import com.example.AdvocateLink.models.Employee;
import com.example.AdvocateLink.models.Manageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManageableRepository extends JpaRepository<Manageable,Long> {
    @Query("SELECT SUM(e.salary) FROM Manageable e where TYPE(e) = Employee")
    Double sumAllSalary();
    @Query("select count(*) from Manageable e where TYPE(e) = Employee")
    Integer countEmployee();
    @Query("select count(*) from Manageable e where TYPE(e) = Client")
    Integer countClients();
    @Query("SELECT e FROM Manageable e WHERE TYPE(e) = Employee")
    Page<Employee> findAllEmployees(Pageable pageable);
    @Query("SELECT e FROM Manageable e WHERE TYPE(e) = Client")
    Page<Client> findAllClient(Pageable pageable);
    @Query("SELECT e from Manageable e where type(e) = Employee and e.id=:id")
    Optional<Employee> findEmployeeById(Long id);
    @Query("SELECT e from Manageable e where type(e) = Client and e.id=:id")
    Optional<Client> findClientById(Long id);
}
