package com.example.practice.enitity.request;

import java.util.Optional;


public record EmpPageRequest(
            Optional<String> name,
            Optional<String> department,
            Optional<String> role,
            PaginationRequest pagination
    ) {}

