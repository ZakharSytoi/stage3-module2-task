package com.mjc.school.service.aspects;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {
    @Pointcut("execution(* com.mjc.school.service.impl.AuthorService.update(*))")
    public void authorDtoUpdate(){

    }
    @Pointcut("execution(* com.mjc.school.service.impl.AuthorService.create(*))")
    public void authorDtoCreate(){

    }

    @Pointcut("authorDtoUpdate() || authorDtoCreate()")
    public void authorServiceUpdateAndCreateMethods(){

    }
    @Pointcut("execution(* com.mjc.school.service.impl.NewsService.update(*))")
    public void newsDtoUpdate(){

    }
    @Pointcut("execution(* com.mjc.school.service.impl.NewsService.create(*))")
    public void newsDtoCreate(){

    }

    @Pointcut("newsDtoUpdate() || newsDtoCreate()")
    public void newsServiceUpdateAndCreateMethods(){

    }

    @Pointcut("execution(* com.mjc.school.service.impl.*Service.*ById(Long))")
    public void readDeleteByID(){

    }
}
