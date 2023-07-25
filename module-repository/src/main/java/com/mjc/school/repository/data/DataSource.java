package com.mjc.school.repository.data;

import com.mjc.school.repository.model.impl.AuthorModel;
import com.mjc.school.repository.model.impl.NewsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class DataSource {

    private final List<NewsModel> newsList;
    private final List<AuthorModel> authorList;
    @Autowired
    DataSource(NewsData newsData, AuthorData authorData){
        newsList = newsData.getNewsList();
        authorList = authorData.getAuthorList();
    }

    public List<NewsModel> getNewsList() {
        return newsList;
    }

    public List<AuthorModel> getAuthorList(){
        return authorList;
    }
}
