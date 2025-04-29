package com.example.practice.service;

import com.example.practice.enitity.Employee;
import com.example.practice.enitity.request.EmpPageRequest;
import com.example.practice.enitity.request.EmployeeRequest;
import com.example.practice.repository.EmployeeRepo;
import com.example.practice.specification.EmployeeSpecification;
import com.example.practice.utills.Utills;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private final EmployeeRepo employeeRepo;
    private final ObjectMapper objectMapper;
    public EmployeeService(EmployeeRepo employeeRepo, ObjectMapper objectMapper) {
        this.employeeRepo = employeeRepo;
        this.objectMapper = objectMapper;
    }

    public Employee addOrUpdateEmployee(EmployeeRequest employeeReq) throws JsonMappingException {
        if (employeeReq.getId() != null && employeeRepo.existsById(employeeReq.getId())) {

            Employee existingEmployee = employeeRepo.findById(employeeReq.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + employeeReq.getId()));

            objectMapper.updateValue(existingEmployee, employeeReq);
            return employeeRepo.save(existingEmployee);
        }

        // Create new employee
        Employee newEmployee = objectMapper.convertValue(employeeReq, Employee.class);
        return employeeRepo.save(newEmployee);
    }


    public Employee GetEmployeeById(Integer id) {
        Employee employee = employeeRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + id));
        return employee;
    }

    public Page<Employee> getAllEmployee(EmpPageRequest pageRequest){
        Specification<Employee> spec = new EmployeeSpecification(pageRequest);
        Pageable pageable = Utills.toPageable(pageRequest.pagination());
       Page<Employee> empList =  employeeRepo.findAll(spec,pageable);
        return new PageImpl<>(
                empList.getContent(),
                pageable,
                empList.getTotalElements()
        );
    }

}
