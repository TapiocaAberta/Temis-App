package com.sjcdigital.temis.domain.database.repository;

import android.content.Context;

import com.sjcdigital.temis.domain.database.dao.GenericDao;
import com.sjcdigital.temis.domain.model.Author;
import com.sjcdigital.temis.domain.model.Law;
import com.sjcdigital.temis.domain.model.LawList;

import java.util.List;

/**
 * Created by bruno.santiago on 31/10/2016.
 */

public class LawRepository extends GenericDao<LawList> {
    private static LawRepository dao;

    public static LawRepository getInstance(Context ctx) {
        if (dao == null) {
            dao = new LawRepository(ctx);
        }
        return dao;
    }

    public LawRepository(Context context) {
        super(context, LawList.class);
    }


    public void saveList(final List<LawList> lawLists, final Author author){
        for (LawList law: lawLists) {
            law.setmAuthor(author);
            saveOrUpdate(law);
        }
    }
}
