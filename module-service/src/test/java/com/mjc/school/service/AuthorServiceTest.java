package com.mjc.school.service;

import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.impl.AuthorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AuthorServiceTest {
    static AuthorService authorService;

    @BeforeAll
    public static void setService() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
        authorService = ctx.getBean("authorService", AuthorService.class);
    }

    @Test
    void getAllTest() {
        Assertions.assertEquals(22, authorService.readAll().size());
    }

    @Test
    void create() {
        AuthorDtoRequest author = new AuthorDtoRequest(1L, "Test Name");
        authorService.create(author);
        Assertions.assertAll(() -> Assertions.assertEquals(21, authorService.readAll().get(authorService.readAll().size() - 1).id()),
                () -> Assertions.assertEquals(author.name(), authorService.readAll().get(authorService.readAll().size() - 1).name()));
                }

    @Test
    void getById() {
        AuthorDtoRequest author = new AuthorDtoRequest(1L, "Test Name");
        authorService.create(author);
        Assertions.assertAll(() -> Assertions.assertEquals(21, authorService.readById(21L).id()),
                () -> Assertions.assertEquals(author.name(), authorService.readById(21L).name()));
    }


    @Test
    void update() {
        AuthorDtoRequest author = new AuthorDtoRequest(10L, "Test Name");
        authorService.update(author);
        Assertions.assertAll(() -> Assertions.assertEquals(10, authorService.readById(10L).id()),
                () -> Assertions.assertEquals(author.name(), authorService.readById(10L).name()));
    }


}
