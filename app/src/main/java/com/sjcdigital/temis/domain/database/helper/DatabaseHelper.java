package com.sjcdigital.temis.domain.database.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.sjcdigital.temis.domain.model.Author;
import com.sjcdigital.temis.domain.model.LawList;

/**
 * Created by bruno.santiago on 31/10/2016.
 */

public class DatabaseHelper<E> extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "temis.data";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource src) {
        try {
            TableUtils.createTable(src, Author.class);
            TableUtils.createTable(src, LawList.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource src, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(src, Author.class, true);
            TableUtils.dropTable(src, LawList.class, true);
            onCreate(db, src);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        super.close();
    }
}
