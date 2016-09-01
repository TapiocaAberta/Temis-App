package com.sjcdigital.temis.view.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.concurrent.atomic.AtomicLong;

import butterknife.ButterKnife;

/**
 * Activity abstrata que servirá como base para todas as outras activities do app.
 */
public class BaseActivity extends AppCompatActivity {

    private static final String ACTIVITY_ID = "ACTIVITY_ID";
    private static final AtomicLong NEXT_ID = new AtomicLong(0);

    private long mActivityId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mActivityId = savedInstanceState != null ?
                savedInstanceState.getLong(ACTIVITY_ID) : NEXT_ID.getAndIncrement();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(ACTIVITY_ID, mActivityId);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
