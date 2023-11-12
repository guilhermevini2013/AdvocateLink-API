package com.example.AdvocateLink.repostories;

import com.example.AdvocateLink.models.Manageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ManageableRepository extends JpaRepository<Manageable,Long> {
    @Query("SELECT SUM(e.salary) FROM Manageable e")
    Double sumAllSalary();
    @Query("select count(*) from Manageable e where e.salary != 0.0")
    Integer countEmployee();
}
