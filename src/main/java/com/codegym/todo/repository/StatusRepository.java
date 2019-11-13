package com.codegym.todo.repository;

import com.codegym.todo.model.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StatusRepository extends CrudRepository<Status, Long> {
    Status findByName(String name);
}

