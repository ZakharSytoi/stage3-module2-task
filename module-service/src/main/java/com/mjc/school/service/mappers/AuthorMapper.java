package com.mjc.school.service.mappers;

import com.mjc.school.repository.model.impl.AuthorModel;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AuthorMapper {
    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    List<AuthorDtoResponse> authorListToDtoList(List<AuthorModel> authorList);

    AuthorDtoResponse authorToDto(AuthorModel author);

    AuthorModel dtoToAuthor(AuthorDtoRequest authorRequest);
}
