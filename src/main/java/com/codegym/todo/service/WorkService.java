package com.codegym.todo.service;

import com.codegym.todo.model.Work;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WorkService {
    Page<Work> findAll(Pageable pageable);

    Work findById(Long id);

    Work save(Work work);

    Long count();

    void deleteById(Long id);

    void delete(Work work);

    Work findByName(String name);

}
