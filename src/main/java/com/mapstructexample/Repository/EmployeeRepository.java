package com.mapstructexample.Repository;

import com.mapstructexample.Entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/*
A simple Spring Data JPA repository to handle database operations.
Allows us to do operations on our Entity Objects (what is stored in DB)

 */
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
}
