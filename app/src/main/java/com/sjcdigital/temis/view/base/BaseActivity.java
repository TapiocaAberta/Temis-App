package com.sjcdigital.temis.view.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.concurrent.atomic.AtomicLong;

import butterknife.ButterKnife;

/**
 * Activity abstrata que servirá como base para todas as outras activities do app.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
