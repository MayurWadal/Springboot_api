package com.example.practice.enitity.request;

import lombok.Data;

@Data
public class EmployeeRequest {
    private Integer id;

    private String name;

    private String address;

    private Long phone;

    private DepartmentRequest dept;
}
