package com.mjc.school.service.mappers;

import com.mjc.school.repository.model.impl.NewsModel;
import com.mjc.school.service.dto.NewsDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface NewsMapper {
    NewsMapper INSTANCE = Mappers.getMapper(NewsMapper.class);

    List<NewsDtoResponse> newsListToDtoList(List<NewsModel> newsLIst);

    NewsDtoResponse newsToDto(NewsModel news);

    @Mappings(value = {@Mapping(target = "createDate", ignore = true), @Mapping(target = "lastUpdatedDate", ignore = true)})
    NewsModel dtoToNews(com.mjc.school.service.dto.NewsDtoRequest newsRequest);
}
