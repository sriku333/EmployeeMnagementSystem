package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


import java.util.Optional;


@SuppressWarnings("unused")
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmployeeId(String employeeId);
    @Query("select e from Employee e order by e.salary DESC, e.name")
    Page<Employee> findByOrderBySalaryDescNameAsc(Pageable pageable);
}
