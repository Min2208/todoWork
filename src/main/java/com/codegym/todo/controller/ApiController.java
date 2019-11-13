package com.codegym.todo.controller;

import com.codegym.todo.model.Work;
import com.codegym.todo.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class ApiController {

    @Autowired
    private WorkService workService;


    @RequestMapping(value = "/todo", method = RequestMethod.GET)
    public ResponseEntity<Page<Work>> listWorks() {
        Pageable pageable = new PageRequest(0, 2);
        Page<Work> works = workService.findAll(pageable);

        if (works.isEmpty()) {
            return new ResponseEntity<Page<Work>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<Page<Work>>(works, HttpStatus.OK);
    }


    @RequestMapping(value = "/todo/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Work> getWork(@PathVariable long id) {
        Work work = workService.findById(id);

        if (work == null) {
            return new ResponseEntity<Work>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Work>(work, HttpStatus.OK);
    }

    @RequestMapping(value = "/todo", method = RequestMethod.POST)
    public ResponseEntity<Void> createCustomer(@Validated @RequestBody Work work, UriComponentsBuilder ucBuilder) {
        workService.save(work);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/todo/{id}").buildAndExpand(work.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/todo/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Work> updateCustomer(@PathVariable("id") long id,@Validated @RequestBody Work work) {

        Work currentWork= workService.findById(id);

        if (currentWork == null) {
            return new ResponseEntity<Work>(HttpStatus.NOT_FOUND);
        }

        currentWork.setWorkName(work.getWorkName());
        currentWork.setStartDate(work.getStartDate());
        currentWork.setEndDate(work.getEndDate());
        currentWork.setStatus(work.getStatus());

        workService.save(currentWork);
        return new ResponseEntity<Work>(currentWork, HttpStatus.OK);
    }

    @RequestMapping(value = "/todo/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Work> deleteCustomer(@PathVariable("id") long id) {
        Work work = workService.findById(id);

        if (work == null) {
            return new ResponseEntity<Work>(HttpStatus.NOT_FOUND);
        }

        workService.deleteById(id);
        return new ResponseEntity<Work>(HttpStatus.NO_CONTENT);
    }

}
