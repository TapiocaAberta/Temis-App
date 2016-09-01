package com.sjcdigital.temis.view;

import android.os.Bundle;
import android.widget.TextView;

import com.sjcdigital.temis.R;
import com.sjcdigital.temis.view.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tvHello) TextView tvHello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        tvHello.setText("Teste");
    }
}
