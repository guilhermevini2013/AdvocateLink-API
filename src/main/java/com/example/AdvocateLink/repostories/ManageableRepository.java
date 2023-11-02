package com.example.AdvocateLink.repostories;

import com.example.AdvocateLink.models.Manageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
public interface ManageableRepository extends JpaRepository<Manageable,Long> {
}
