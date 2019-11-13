package com.codegym.todo;

import com.codegym.todo.formatter.StatusFormatter;
import com.codegym.todo.model.Status;
import com.codegym.todo.repository.StatusRepository;
import com.codegym.todo.repository.WorkRepository;
import com.codegym.todo.service.StatusService;
import com.codegym.todo.service.WorkService;
import com.codegym.todo.service.impl.StatusServiceImpl;
import com.codegym.todo.service.impl.WorkServiceImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@SpringBootApplication
public class TodoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(TodoApplication.class, args);
    }

    @Bean
    public WorkService workService() {
        return new WorkServiceImpl();
    }

    @Bean
    public StatusService statusService() {
        return new StatusServiceImpl();
    }

    @Autowired
    private StatusRepository statusRepository;

    @Override
    public void run(String... args) throws Exception {
        if (statusRepository.findByName("Planning") == null) {
            statusRepository.save(new Status("Planning"));
        }
        if (statusRepository.findByName("Doing") == null) {
            statusRepository.save(new Status("Doing"));
        }
        if (statusRepository.findByName("Complete") == null) {
            statusRepository.save(new Status("Complete"));
        }
    }


    @Configuration
    class WebConfig implements WebMvcConfigurer, ApplicationContextAware {

        private ApplicationContext appContext;

        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            appContext = applicationContext;
        }

		@Override
		public void addFormatters(FormatterRegistry registry) {
			StatusService statusService = appContext.getBean(StatusService.class);
			Formatter locationFormatter = new StatusFormatter(statusService);
			registry.addFormatter(locationFormatter);
		}



    }

}
