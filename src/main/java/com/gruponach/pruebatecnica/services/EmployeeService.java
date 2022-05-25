package com.gruponach.pruebatecnica.services;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.gruponach.pruebatecnica.models.Employee;
import com.gruponach.pruebatecnica.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findEmployeesWithLambda(Long id){
        return employeeRepository.findAll().stream().filter(employee -> employee.getJob_id() == id).collect(Collectors.toList());
    }

    public List<Employee> findEmployeeByJobIdLambda(Long jobId) {
        return employeeRepository.findEmployeeByJobId(jobId).stream().sorted((e1,e2) -> e1.getLast_name().compareTo(e2.getLast_name())).collect(Collectors.toList());
    }

    public List<Employee> findEmployeeByJobId(Long jobId) {
        List<Employee> list = employeeRepository.findEmployeeByJobId(jobId);
        Collections.sort(list, new Comparator<Employee>() {
            @Override
            public int compare(Employee e1, Employee e2) {
                return e1.getLast_name().compareTo(e2.getLast_name());
            }
        });
        return list;
    }

    public List<Employee> findIdEmployee(String name,String last_name) {
        return employeeRepository.findIdEmployee(name,last_name);
    }

    public boolean findbyId(Long id) {
        return employeeRepository.existsById(id);
    }

    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }
}
