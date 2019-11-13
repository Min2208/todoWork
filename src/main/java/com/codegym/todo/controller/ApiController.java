package com.codegym.todo.controller;

import com.codegym.todo.model.Work;
import com.codegym.todo.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApiController {

    @Autowired
    private WorkService workService;

    @ResponseBody
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ResponseEntity<Page<Work>> listWorks() {
        Pageable pageable = new PageRequest(0, 2);
        Page<Work> works = workService.findAll(pageable);
        if (works.isEmpty()) {
            return new ResponseEntity<Page<Work>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Page<Work>>(works, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/home/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Work> getWork(@PathVariable long id) {
        Work work = workService.findById(id);
        if (work == null) {
            return new ResponseEntity<Work>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Work>(work, HttpStatus.OK);
    }


}
