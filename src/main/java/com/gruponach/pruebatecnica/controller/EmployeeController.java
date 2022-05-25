package com.gruponach.pruebatecnica.controller;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;
import com.gruponach.pruebatecnica.models.Employee;
import com.gruponach.pruebatecnica.services.EmployeeService;
import com.gruponach.pruebatecnica.services.JobsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final JobsService jobsService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, JobsService jobsService) {
        this.employeeService = employeeService;
        this.jobsService = jobsService;
    }

    @PostMapping("/add")
    public @ResponseBody ResponseEntity<String> addEmployee(@RequestBody Employee employee) {
        Boolean success = true;
        Long id;
        LocalDate now = LocalDate.now();
        LocalDate birthdate = Instant.ofEpochMilli(employee.getBirthdate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();

        Period period = Period.between(birthdate, now);

        if(employeeService.findIdEmployee(employee.getName(),employee.getLast_name()).size() > 0){
            if(period.getYears() < 18 || jobsService.findJobById(employee.getJob_id()).isPresent()) {
                success = false;
                return new ResponseEntity<String>("{\n'id': null"+"\n'success':"+success+"\n}", HttpStatus.BAD_REQUEST);
            }
        }

        
        employeeService.addEmployee(employee);
        id = employeeService.findIdEmployee(employee.getName(),employee.getLast_name()).get(0).getId();
        return new ResponseEntity<String>("{\n'id': "+id+"\n'success':"+success+"\n}", HttpStatus.OK);
    }

    @GetMapping(path = "getAll/{id}")
    public List<Employee> findEmployeesWithLambda(@PathVariable("id") Long jobId) {
        return employeeService.findEmployeesWithLambda(jobId);
    }

    @GetMapping(path="getSort/lambda/{id}")
    public List<Employee> findEmployeeByJobIdLambda(@PathVariable("id") Long jobId) {
        return employeeService.findEmployeeByJobIdLambda(jobId);
    }

    @GetMapping(path="getSort/{id}")
    public List<Employee> findEmployeeByJobId(@PathVariable("id") Long jobId) {
        return employeeService.findEmployeeByJobId(jobId);
    }
}
