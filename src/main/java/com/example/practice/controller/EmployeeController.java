package com.example.practice.controller;

import com.example.practice.enitity.Employee;
import com.example.practice.enitity.request.EmpPageRequest;
import com.example.practice.enitity.request.EmployeeRequest;
import com.example.practice.service.EmployeeService;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/insert-employee")
    private ResponseEntity<Employee> insertEmployee(@RequestBody EmployeeRequest employee) throws JsonMappingException {
//        Employee emp = objectMapper.convertValue(employee, Employee.class);
        Employee response = employeeService.addOrUpdateEmployee(employee);
        return ResponseEntity.ok(response);
    }

    @GetMapping("employee-by-id")
    private ResponseEntity<Employee> getEmployeeById(@RequestParam Integer id)  {
        return ResponseEntity.ok(employeeService.GetEmployeeById(id));
    }

    @PostMapping()
    private ResponseEntity<Page<Employee>> listEmployee(@RequestBody EmpPageRequest pageRequest) {
        Page<Employee> response = employeeService.getAllEmployee(pageRequest);
        return ResponseEntity.ok(response);
    }

}
