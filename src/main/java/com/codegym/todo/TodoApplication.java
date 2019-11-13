package com.codegym.todo;

import com.codegym.todo.model.Status;
import com.codegym.todo.repository.StatusRepository;
import com.codegym.todo.repository.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TodoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}

	@Autowired
	private StatusRepository statusRepository;

	@Override
	public void run(String... args) throws Exception {
		if (statusRepository.findByName("Planning")==null){
			statusRepository.save(new Status("Planning"));
		}
		if (statusRepository.findByName("Doing")==null){
			statusRepository.save(new Status("Doing"));
		}
		if (statusRepository.findByName("Complete")==null){
			statusRepository.save(new Status("Complete"));
		}
	}
}
