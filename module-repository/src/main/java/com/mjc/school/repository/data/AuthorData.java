package com.mjc.school.repository.data;

import com.mjc.school.repository.model.impl.AuthorModel;
import com.mjc.school.repository.util.DataReader;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Component
@Scope("singleton")
public class AuthorData {
    private static final String AUTHORS_FILE_NAME = "authors";

    private final List<AuthorModel> authorList;

    private AuthorData() {
        authorList = new ArrayList<>();
        List<String> authors = DataReader.read(AUTHORS_FILE_NAME);
        List<Integer> prevs = new LinkedList<>();
        Random rnd = new Random();
        for (long i = 1; i <= 20; i++) {
            int index = rnd.nextInt(authors.size());
            if(prevs.contains(index)) {
                i--;
            }
            else {
                prevs.add(index);
                AuthorModel am = new AuthorModel(i, authors.get(index));
                authorList.add(am);
            }

        }
    }

    public List<AuthorModel> getAuthorList() {
        return authorList;
    }
}

