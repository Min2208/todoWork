package com.codegym.todo.service;

import com.codegym.todo.model.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StatusService {
    List<Status> findAll();

    Status findById(Long id);

    Status save(Status status);

    void deleteById(Long id);

    void delete(Status status);

    Status findByName(String name);
}
