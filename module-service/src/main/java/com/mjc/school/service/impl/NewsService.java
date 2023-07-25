package com.mjc.school.service.impl;

import com.mjc.school.repository.impl.NewsRepository;
import com.mjc.school.repository.model.impl.NewsModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.mappers.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.mjc.school.service.exceptions.ServiceErrorCode.NEWS_ID_DOES_NOT_EXIST;

@Service
public class NewsService implements BaseService<NewsDtoRequest, NewsDtoResponse, Long> {

    private final NewsRepository repository;

    @Autowired
    public NewsService(NewsRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<NewsDtoResponse> readAll() {
        return NewsMapper.INSTANCE.newsListToDtoList(repository.readAll());
    }

    @Override
    public NewsDtoResponse readById(Long id) {
        return NewsMapper.INSTANCE.newsToDto(repository.readById(id).orElseThrow(()->new NotFoundException(String.format(String.valueOf(NEWS_ID_DOES_NOT_EXIST.getMessage()), id))));
    }

    @Override
    public NewsDtoResponse create(NewsDtoRequest createRequest) {
        /*Add validation*/
        NewsModel model = NewsMapper.INSTANCE.dtoToNews(createRequest);
        model.setCreateDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        model.setLastUpdatedDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        return NewsMapper.INSTANCE.newsToDto(repository.create(model));
    }

    @Override
    public NewsDtoResponse update(NewsDtoRequest updateRequest) {
        if (repository.existById(updateRequest.id())) {
            NewsModel model = NewsMapper.INSTANCE.dtoToNews(updateRequest);
            model.setLastUpdatedDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
            return NewsMapper.INSTANCE.newsToDto(repository.update(model));
        } else {
            throw new NotFoundException(String.format(String.valueOf(NEWS_ID_DOES_NOT_EXIST.getMessage()), updateRequest.id()));
        }
    }

    @Override
    public boolean deleteById(Long id) {
        if (repository.existById(id)) {
            return repository.deleteById(id);
        } else {
            throw new NotFoundException(String.format(String.valueOf(NEWS_ID_DOES_NOT_EXIST.getMessage()), id));
        }
    }
}
