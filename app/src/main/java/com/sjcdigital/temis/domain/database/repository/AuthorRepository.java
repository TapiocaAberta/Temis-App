package com.sjcdigital.temis.domain.database.repository;

import android.content.Context;

import com.sjcdigital.temis.domain.database.dao.GenericDao;
import com.sjcdigital.temis.domain.model.Author;

import java.util.List;

/**
 * Created by bruno.santiago on 31/10/2016.
 */

public class AuthorRepository extends GenericDao<Author> {
    private static AuthorRepository dao;

    public static AuthorRepository getInstance(Context ctx) {
        if (dao == null) {
            dao = new AuthorRepository(ctx);
        }
        return dao;
    }

    public void saveList(final List<Author> authors){
        for (Author author: authors) {
            saveOrUpdate(author);
        }
    }
    public AuthorRepository(Context context) {
        super(context, Author.class);
    }
}
