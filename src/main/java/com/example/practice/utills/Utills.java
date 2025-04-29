package com.example.practice.utills;

import com.example.practice.enitity.request.PaginationRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class Utills {

    public static Pageable toPageable(PaginationRequest request) {
        String sortBy = request.sortBy().orElse("id"); // default sort field
        Sort.Direction direction = request.sortDir()
                .map(dir -> dir.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC)
                .orElse(Sort.Direction.ASC); // default direction

        return PageRequest.of(request.page(), request.size(), Sort.by(direction, sortBy));
    }
}
