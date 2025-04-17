package com.example.practice.enitity.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DepartmentRequest {
    private Integer id; // can be null if new
    private String name;
}
