package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.data.DataSource;
import com.mjc.school.repository.model.impl.NewsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class NewsRepository implements BaseRepository<NewsModel, Long> {
    private final DataSource dataSource;
    @Autowired
    public NewsRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<NewsModel> readAll() {
        return dataSource.getNewsList();
    }

    @Override
    public Optional<NewsModel> readById(Long id) {
        return dataSource
                .getNewsList()
                .stream()
                .filter(entry -> entry.getId().equals(id))
                .findFirst();
    }

    @Override
    public NewsModel create(NewsModel entity) {
        List<NewsModel> newsModelList = dataSource.getNewsList();
        if (newsModelList.isEmpty()) {
            entity.setId(1L);
        } else {
            entity.setId(newsModelList.get(dataSource.getNewsList().size() - 1).getId() + 1);
        }
        newsModelList.add(entity);
        return newsModelList.get(dataSource.getNewsList().size() - 1);
    }

    @Override
    public NewsModel update(NewsModel entity) {
        Optional<NewsModel>newsModelOptional = readById(entity.getId());
        NewsModel newsModel = null;
        if (newsModelOptional.isPresent()){
            newsModel = newsModelOptional.get();
            newsModel.setAuthorId(entity.getAuthorId());
            newsModel.setTitle(entity.getTitle());
            newsModel.setContent(entity.getContent());
            newsModel.setLastUpdatedDate(entity.getLastUpdatedDate());
        }
        return newsModel;
    }

    @Override
    public boolean deleteById(Long id) {
        for (int i = 0; i < dataSource.getNewsList().size(); i++) {
            if (id.equals(dataSource.getNewsList().get(i).getId())) {
                dataSource.getNewsList().remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean existById(Long id) {
        for (NewsModel model :
                dataSource.getNewsList()) {
            if(model.getId().equals(id)) return true;
        }
        return false;
    }
}
