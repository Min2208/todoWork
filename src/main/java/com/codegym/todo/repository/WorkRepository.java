package com.codegym.todo.repository;

import com.codegym.todo.model.Work;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface WorkRepository extends PagingAndSortingRepository<Work, Long> {
    Work findByWorkNameContains(String name);
}
