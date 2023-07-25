package com.mjc.school.service;

import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.impl.NewsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class NewsServiceTest {
    static NewsService newsService;

    @BeforeAll
    public static void setService() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
        newsService = ctx.getBean("newsService", NewsService.class);
    }

    @Test
    void getAllTest() {
        Assertions.assertEquals(22, newsService.readAll().size());
    }

    @Test
    void create() {
        NewsDtoRequest news = new NewsDtoRequest(1L, "Test Title", "Test Content", 10L);
        newsService.create(news);
        Assertions.assertAll(() -> Assertions.assertEquals(21, newsService.readAll().get(newsService.readAll().size() - 1).id()),
                () -> Assertions.assertEquals(news.title(), newsService.readAll().get(newsService.readAll().size() - 1).title()),
                () -> Assertions.assertEquals(news.content(), newsService.readAll().get(newsService.readAll().size() - 1).content()),
                () -> Assertions.assertEquals(news.authorId(), newsService.readAll().get(newsService.readAll().size() - 1).authorId()));
    }

    @Test
    void getById() {
        NewsDtoRequest news = new NewsDtoRequest(1L, "Test Title", "Test Content", 10L);
        newsService.create(news);
        Assertions.assertAll(() -> Assertions.assertEquals(21, newsService.readById(21L).id()),
                () -> Assertions.assertEquals(news.title(), newsService.readById(21L).title()),
                () -> Assertions.assertEquals(news.content(), newsService.readById(21L).content()),
                () -> Assertions.assertEquals(news.authorId(), newsService.readById(21L).authorId()));
    }


    @Test
    void update() {
        NewsDtoRequest news = new NewsDtoRequest(10L, "Test Title", "Test Content", 10L);
        newsService.update(news);
        Assertions.assertAll(() -> Assertions.assertEquals(10, newsService.readById(10L).id()),
                () -> Assertions.assertEquals(news.title(), newsService.readById(10L).title()),
                () -> Assertions.assertEquals(news.content(), newsService.readById(10L).content()),
                () -> Assertions.assertEquals(news.authorId(), newsService.readById(10L).authorId()));
    }


}