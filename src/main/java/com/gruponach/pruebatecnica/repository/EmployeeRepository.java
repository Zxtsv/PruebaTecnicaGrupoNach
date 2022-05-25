package com.gruponach.pruebatecnica.repository;
import java.util.List;
import com.gruponach.pruebatecnica.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Modifying
    @Query(value= "SELECT * FROM Employees WHERE job_id = :id", nativeQuery = true)
    List<Employee> findEmployeeByJobId(@Param("id") Long jobId);

    @Modifying
    @Query(value= "SELECT * FROM Employees WHERE name = :name AND last_name = :last_name", nativeQuery = true)
    List<Employee> findIdEmployee(@Param("name") String name, @Param("last_name") String last_name);
}
