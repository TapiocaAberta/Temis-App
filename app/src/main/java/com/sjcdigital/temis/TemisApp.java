package com.sjcdigital.temis;

import android.app.Application;

import com.sjcdigital.temis.domain.database.helper.DatabaseManager;

public class TemisApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        DatabaseManager.init(this);
    }
}
