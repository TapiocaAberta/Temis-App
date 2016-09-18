package com.sjcdigital.temis.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sjcdigital.temis.R;
import com.sjcdigital.temis.domain.model.Author;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AuthorDetailActivity extends AppCompatActivity {

    @BindView(R.id.ivPhoto)
    ImageView ivPhoto;
    @BindView(R.id.tvInfo)
    TextView tvInfo;
    @BindView(R.id.tvEmail)
    TextView tvEmail;
    @BindView(R.id.tvPhone)
    TextView tvPhone;
    @BindView(R.id.tvPartido)
    TextView tvPartido;
    @BindView(R.id.tvLegislature)
    TextView tvLegislature;
    @BindView(R.id.tvWorkspace)
    TextView tvWorkspace;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private Author author;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author_detail);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        author = getIntent().getExtras().getParcelable("pAuthor");
        if (author != null) {
            toolbar.setTitle(author.name);

            tvInfo.setText(author.info);
            tvEmail.setText(author.email);
            tvPhone.setText(author.phone);
            tvLegislature.setText(author.legislature);
            tvPartido.setText(author.politicalParty);
            tvWorkspace.setText(author.workplace);

            Glide.with(this)
                    .load(author.photo)
                    .crossFade()
                    .into(ivPhoto);
        }

    }

    @OnClick(R.id.fab)
    void openLaws() {
        Intent intent = new Intent(this, LawsActivity.class);
        intent.putExtra("authorName",author.name);
        startActivity(intent);
    }
}
