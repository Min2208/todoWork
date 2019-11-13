package com.codegym.todo.service.impl;

import com.codegym.todo.model.Status;
import com.codegym.todo.repository.StatusRepository;
import com.codegym.todo.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class StatusServiceImpl implements StatusService {

    @Autowired
    private StatusRepository statusRepository;


    @Override
    public List<Status> findAll() {
        return (List<Status>) statusRepository.findAll();
    }

    @Override
    public Status findById(Long id) {
        return statusRepository.findById(id).get();
    }

    @Override
    public Status save(Status status) {
        return statusRepository.save(status);
    }

    @Override
    public void deleteById(Long id) {
        statusRepository.deleteById(id);
    }

    @Override
    public void delete(Status status) {
        statusRepository.delete(status);
    }

    @Override
    public Status findByName(String name) {
        return statusRepository.findByName(name);
    }
}
