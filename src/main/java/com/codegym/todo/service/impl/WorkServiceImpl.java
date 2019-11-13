package com.codegym.todo.service.impl;

import com.codegym.todo.model.Work;
import com.codegym.todo.repository.WorkRepository;
import com.codegym.todo.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class WorkServiceImpl implements WorkService {
    @Autowired
    private WorkRepository workRepository;

    @Override
    public Page<Work> findAll(Pageable pageable) {
        return workRepository.findAll(pageable);
    }

    @Override
    public Work findById(Long id) {
        return workRepository.findById(id).get();
    }

    @Override
    public Work save(Work work) {
        return workRepository.save(work);
    }

    @Override
    public Long count() {
        return workRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        workRepository.deleteById(id);
    }

    @Override
    public void delete(Work work) {
        workRepository.delete(work);
    }
}
