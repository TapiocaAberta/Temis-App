package com.sjcdigital.temis.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.sjcdigital.temis.R;
import com.sjcdigital.temis.domain.model.Author;
import com.sjcdigital.temis.domain.model.LawList;
import com.sjcdigital.temis.domain.service.LawsService;
import com.sjcdigital.temis.presenter.LawsContract;
import com.sjcdigital.temis.presenter.LawsPresenter;
import com.sjcdigital.temis.view.adapter.LawsAdapter;
import com.sjcdigital.temis.view.base.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bruno.santiago on 17/09/2016.
 */
public class LawsActivity extends BaseActivity implements LawsContract.View, View.OnClickListener {


    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

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

    LawsPresenter presenter;

    private LawsService lawsService;
    private Author author;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temis_view);
        ButterKnife.bind(this);
        lawsService = new LawsService(this);
        presenter = new LawsPresenter(lawsService);

        author = (Author) getIntent().getBundleExtra("authorName").getSerializable("author");
        setToolbar();
        initialize();

        fabOpen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        rotateForward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_forward);
        rotateBackward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_backward);

        fab.setOnClickListener(this);
        fabSync.setOnClickListener(this);
        fabShare.setOnClickListener(this);
    }
    protected void setToolbar() {
        toolbar.setTitle(R.string.projeto_leis);
        toolbar.setSubtitle(author.getName()    );
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    private void initialize() {
        presenter.setView(this);
        presenter.loadLaws(author);
    }

    @Override
    public void setupLaws(List<LawList> laws) {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        LawsAdapter lawsAdapter = new LawsAdapter(this, laws);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(lawsAdapter);
    }

    @Override
    public void showLoadingLayout() {
        successContainer.setVisibility(View.GONE);
        errorContainer.setVisibility(View.GONE);
        loadingContainer.setVisibility(View.VISIBLE);
        emptyContainer.setVisibility(View.GONE);
    }

    @Override
    public void showErrorLayout() {
        successContainer.setVisibility(View.GONE);
        errorContainer.setVisibility(View.VISIBLE);
        loadingContainer.setVisibility(View.GONE);
        emptyContainer.setVisibility(View.GONE);
    }

    @Override
    public void showSuccessLayout() {
        successContainer.setVisibility(View.VISIBLE);
        errorContainer.setVisibility(View.GONE);
        loadingContainer.setVisibility(View.GONE);
        emptyContainer.setVisibility(View.GONE);
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
                presenter.apiConsume(author);
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
