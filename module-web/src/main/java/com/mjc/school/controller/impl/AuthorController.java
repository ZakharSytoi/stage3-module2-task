package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.impl.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class AuthorController implements BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> {
    private final BaseService<AuthorDtoRequest, AuthorDtoResponse, Long> service;

    @Autowired
    public AuthorController(AuthorService authorService){
        this.service = authorService;
    }
    @Override
    public List<AuthorDtoResponse> readAll() {
        return service.readAll();
    }

    @Override
    public AuthorDtoResponse readById(Long id) {
        return service.readById(id);
    }

    @Override
    public AuthorDtoResponse create(AuthorDtoRequest createRequest) {
        return service.create(createRequest);
    }

    @Override
    public AuthorDtoResponse update(AuthorDtoRequest updateRequest) {
        return service.update(updateRequest);
    }

    @Override
    public boolean deleteById(Long id) {
        return service.deleteById(id);
    }
}
