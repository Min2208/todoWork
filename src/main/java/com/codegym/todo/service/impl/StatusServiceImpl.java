package com.codegym.todo.service.impl;

import com.codegym.todo.model.Status;
import com.codegym.todo.service.StatusService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class StatusServiceImpl implements StatusService {
    @Override
    public Page<Status> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Status findById(Long id) {
        return null;
    }

    @Override
    public Status save(Status status) {
        return null;
    }

    @Override
    public Long count() {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void delete(Status status) {

    }
}
