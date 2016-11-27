package com.sjcdigital.temis.view;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sjcdigital.temis.R;
import com.sjcdigital.temis.domain.database.repository.AuthorRepository;
import com.sjcdigital.temis.domain.model.Author;
import com.sjcdigital.temis.domain.service.AuthorService;
import com.sjcdigital.temis.presenter.MainContract;
import com.sjcdigital.temis.presenter.MainPresenter;
import com.sjcdigital.temis.view.adapter.AuthorAdapter;
import com.sjcdigital.temis.view.base.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MainContract.View, View.OnClickListener{

    @BindView(R.id.recyclerview)
    RecyclerView authorList;

    @BindView(R.id.successContainer)
    LinearLayout successContainer;

    @BindView(R.id.errorContainer)
    LinearLayout errorContainer;

    @BindView(R.id.loadingContainer)
    LinearLayout loadingContainer;

    @BindView(R.id.emptyContainer)
    LinearLayout emptyContainer;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.sync)
    FloatingActionButton fabSync;
    @BindView(R.id.share)
    FloatingActionButton fabShare;

    private Boolean isFabOpen = false;

    private Animation fabOpen, fabClose, rotateForward, rotateBackward;

    private MainPresenter presenter;

    private AuthorService authorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setToolbar();
        authorService = new AuthorService(this);
        presenter = new MainPresenter(authorService);
        initialize();

        fabOpen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        rotateForward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_forward);
        rotateBackward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_backward);

        fab.setOnClickListener(this);
        fabSync.setOnClickListener(this);
        fabShare.setOnClickListener(this);
    }

    private void initialize() {
        presenter.setView(this);
        presenter.loadAldermain();
    }

    protected void setToolbar() {
        toolbar.setTitle(R.string.app_name);
        toolbar.setSubtitle(getString(R.string.monitor_legislativo));
        setSupportActionBar(toolbar);
    }


    @OnClick(R.id.btnRetry)
    void retryAldermains() {
        presenter.retryAldermain();
    }

    @Override
    public void setupAuthors(List<Author> authors) {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        AuthorAdapter companyListAdapter = new AuthorAdapter(this, authors);
        authorList.setLayoutManager(manager);
        authorList.setAdapter(companyListAdapter);
    }

    @Override
    public void showLoadingLayout() {
        successContainer.setVisibility(View.GONE);
        errorContainer.setVisibility(View.GONE);
        loadingContainer.setVisibility(View.VISIBLE);
        emptyContainer.setVisibility(View.GONE);
        fab.setVisibility(View.GONE);
    }

    @Override
    public void showErrorLayout() {
        successContainer.setVisibility(View.GONE);
        errorContainer.setVisibility(View.VISIBLE);
        loadingContainer.setVisibility(View.GONE);
        emptyContainer.setVisibility(View.GONE);
        fab.setVisibility(View.GONE);
    }

    @Override
    public void showSuccessLayout() {
        successContainer.setVisibility(View.VISIBLE);
        errorContainer.setVisibility(View.GONE);
        loadingContainer.setVisibility(View.GONE);
        emptyContainer.setVisibility(View.GONE);
        fab.setVisibility(View.VISIBLE);
    }

    @Override
    public void showEmptyLayout() {
        successContainer.setVisibility(View.GONE);
        errorContainer.setVisibility(View.GONE);
        loadingContainer.setVisibility(View.GONE);
        emptyContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch (id) {
            case R.id.fab:
                animateFAB();
                break;
            case R.id.share:
               /* Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
                intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.link_app));
                intent.setType("text/plain");
                startActivity(intent);*/
                animateFAB();
                break;
            case R.id.sync:
                animateFAB();
                presenter.apiConsume();
                break;
        }
    }

    public void animateFAB() {

        if (isFabOpen) {

            fab.startAnimation(rotateBackward);
            fabShare.startAnimation(fabClose);
            fabSync.startAnimation(fabClose);
            fabShare.setClickable(false);
            fabSync.setClickable(false);
            isFabOpen = false;

        } else {
            fab.startAnimation(rotateForward);
            fabSync.startAnimation(fabOpen);
            fabShare.startAnimation(fabOpen);
            fabSync.setClickable(true);
            fabShare.setClickable(true);
            isFabOpen = true;
        }
    }
}
