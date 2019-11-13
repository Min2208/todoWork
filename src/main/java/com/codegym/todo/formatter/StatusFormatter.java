package com.codegym.todo.formatter;

import com.codegym.todo.model.Status;
import com.codegym.todo.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class StatusFormatter implements Formatter {
    @Autowired
    private StatusService statusService;

    public StatusFormatter(StatusService statusService) {
    }

    @Override
    public Status parse(String text, Locale locale) throws ParseException {
        return statusService.findById(Long.valueOf(text));
    }

    @Override
    public String print(Object object, Locale locale) {
        return object.toString();
    }
}
