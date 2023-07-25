package com.mjc.school.repository;

import com.mjc.school.repository.impl.AuthorRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        AuthorRepository ar = applicationContext.getBean("authorRepository", AuthorRepository.class);

    }
}
