package com.imoehl.payrollapi.repository;

import com.imoehl.payrollapi.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
