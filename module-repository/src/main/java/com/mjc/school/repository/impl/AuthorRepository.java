package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.data.DataSource;
import com.mjc.school.repository.model.impl.AuthorModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepository implements BaseRepository<AuthorModel, Long> {

    private final DataSource dataSource;

    @Autowired
    public AuthorRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<AuthorModel> readAll() {
        return dataSource.getAuthorList();
    }

    @Override
    public Optional<AuthorModel> readById(Long id) {
        return dataSource
                .getAuthorList()
                .stream()
                .filter(entry -> entry.getId().equals(id))
                .findFirst();
    }

    @Override
    public AuthorModel create(AuthorModel entity) {
        List<AuthorModel> authorModels = dataSource.getAuthorList();
        if (authorModels.isEmpty()) {
            entity.setId(1L);
        } else {
            entity.setId(authorModels.get(dataSource.getAuthorList().size() - 1).getId() + 1);
        }
        authorModels.add(entity);
        return authorModels.get(dataSource.getAuthorList().size() - 1);
    }

    @Override
    public AuthorModel update(AuthorModel entity) {
        Optional<AuthorModel> authorModelOptional = readById(entity.getId());
        AuthorModel authorModel = null;
        if (authorModelOptional.isPresent()) {
            authorModel = authorModelOptional.get();
            authorModel.setName(entity.getName());
        }
        return authorModel;
    }

    @Override
    public boolean deleteById(Long id) {
        for (int i = 0; i < dataSource.getAuthorList().size(); i++) {
            if (id.equals(dataSource.getAuthorList().get(i).getId())) {
                dataSource.getAuthorList().remove(i);
                dataSource.getNewsList().removeIf(newsModel -> newsModel.getAuthorId().equals(id));
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean existById(Long id) {
        for (AuthorModel model :
                dataSource.getAuthorList()) {
            if (model.getId().equals(id)) return true;
        }
        return false;
    }
}
