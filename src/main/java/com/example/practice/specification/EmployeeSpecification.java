package com.example.practice.specification;

import com.example.practice.enitity.Employee;
import com.example.practice.enitity.request.EmpPageRequest;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class EmployeeSpecification implements Specification<Employee> {
    private final EmpPageRequest pageRequest;


    public EmployeeSpecification(EmpPageRequest pageRequest) {
        this.pageRequest = pageRequest;
    }

    @Override
    public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query , CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        pageRequest.name().ifPresent((String name)->
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name").as(String.class)), "%" + name.toLowerCase() + "%"))
        );

        pageRequest.department().ifPresent(department ->
                predicates.add(criteriaBuilder.equal(root.join("dept").get("name"), department))
        );

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    }

