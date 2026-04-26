package com.thymeleaf.dao;

import com.thymeleaf.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

   //add method to sort by lastName
   public List<Employee> findAllByOrderByLastNameAsc();

}
