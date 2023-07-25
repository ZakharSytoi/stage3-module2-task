package com.mjc.school.service;

import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.impl.AuthorService;
import com.mjc.school.service.impl.NewsService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;



public class Test {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
        try{
            System.out.println(ctx.getBean("authorService", AuthorService.class).create(new AuthorDtoRequest(10L, null)));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        try{
            System.out.println(ctx.getBean("authorService", AuthorService.class).create(new AuthorDtoRequest(null, "a")));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        try{
            System.out.println(ctx.getBean("authorService", AuthorService.class).create(new AuthorDtoRequest(null, null)));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        try{
            System.out.println(ctx.getBean("newsService", NewsService.class).create(new NewsDtoRequest(10L, null, null, null)));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        try{
            System.out.println(ctx.getBean("newsService", NewsService.class).create(new NewsDtoRequest(null, "aaa", null, null)));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        try{
            System.out.println(ctx.getBean("newsService", NewsService.class).create(new NewsDtoRequest(null, "aaaaaaaa", "11", null)));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        try{
            System.out.println(ctx.getBean("newsService", NewsService.class).create(new NewsDtoRequest(null, "bbbbbbbbbb", "bbbbbbbbbbbbbbbb", 1000L)));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        try{
            System.out.println(ctx.getBean("newsService", NewsService.class).update(new NewsDtoRequest(null, "bbbbbbbbbb", "bbbbbbbbbbbbbbbb", 0L)));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }


    }


}
