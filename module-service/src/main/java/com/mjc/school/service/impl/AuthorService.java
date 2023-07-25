package com.mjc.school.service.impl;

import com.mjc.school.repository.impl.AuthorRepository;
import com.mjc.school.repository.model.impl.AuthorModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.exceptions.ValidatorException;
import com.mjc.school.service.mappers.AuthorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

import static com.mjc.school.service.exceptions.ServiceErrorCode.AUTHOR_ID_DOES_NOT_EXIST;

@Service
public class AuthorService implements BaseService<AuthorDtoRequest, AuthorDtoResponse, Long> {
    private final AuthorRepository repository;

    @Autowired
    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<AuthorDtoResponse> readAll() {
        return AuthorMapper.INSTANCE.authorListToDtoList(repository.readAll());
    }

    @Override
    public AuthorDtoResponse readById(Long id) {
        return AuthorMapper.INSTANCE.authorToDto(repository.readById(id)
                .orElseThrow(()->new ValidatorException(String
                        .format(AUTHOR_ID_DOES_NOT_EXIST.getMessage(), id))));
    }

    @Override
    public AuthorDtoResponse create(AuthorDtoRequest createRequest) {
        AuthorModel model = AuthorMapper.INSTANCE.dtoToAuthor(createRequest);
        return AuthorMapper.INSTANCE.authorToDto(repository.create(model));
    }

    @Override
    public AuthorDtoResponse update(AuthorDtoRequest updateRequest) {
        if (repository.existById(updateRequest.id())) {
            AuthorModel model = AuthorMapper.INSTANCE.dtoToAuthor(updateRequest);
            return AuthorMapper.INSTANCE.authorToDto(repository.update(model));
        } else {
            throw new ValidatorException(String
                    .format(AUTHOR_ID_DOES_NOT_EXIST.getMessage(), updateRequest.id()));
        }
    }
    @Override
    public boolean deleteById(Long id) {
        if (repository.existById(id)) {
            return repository.deleteById(id);
        } else {
            throw new ValidatorException(String
                    .format(AUTHOR_ID_DOES_NOT_EXIST.getMessage(), id));
        }
    }
}
