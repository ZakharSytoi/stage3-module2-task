package com.mjc.school.repository.data;

import com.mjc.school.repository.model.impl.AuthorModel;
import com.mjc.school.repository.model.impl.NewsModel;
import com.mjc.school.repository.util.DataReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
@Component
@Scope("singleton")
public class NewsData {
    private static final String CONTENT_FILE_NAME = "content";
    private static final String NEWS_FILE_NAME = "news";
    private final List<NewsModel> newsList;
@Autowired
    private NewsData(AuthorData authorData) {
        newsList = new ArrayList<>();
        List<AuthorModel> authors = authorData.getAuthorList();
        List<String> content = DataReader.read(CONTENT_FILE_NAME);
        List<String> titles = DataReader.read(NEWS_FILE_NAME);
        Random rnd = new Random();
        for (long i = 1; i <= 20; i++) {
            newsList.add(new NewsModel(
                    i,
                    titles.get(rnd.nextInt(titles.size())),
                    content.get(rnd.nextInt(content.size())),
                    LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),
                    authors.get(rnd.nextInt(authors.size())).getId()));
        }
    }


    public List<NewsModel> getNewsList() {
        return newsList;
    }
}
