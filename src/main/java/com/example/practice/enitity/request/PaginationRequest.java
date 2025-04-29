package com.example.practice.enitity.request;

import java.util.Optional;

    public record PaginationRequest(
            int page,                          // Page number (0-based)
            int size,                          // Number of records per page
            Optional<String> sortBy,           // Field to sort by (e.g., "name")
            Optional<String> sortDir           // Sorting direction: "asc" or "desc"
    ) {}

