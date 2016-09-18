package com.sjcdigital.temis.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.sjcdigital.temis.R;
import com.sjcdigital.temis.domain.model.LawList;
import com.sjcdigital.temis.domain.model.Laws;
import com.sjcdigital.temis.domain.service.LawsService;
import com.sjcdigital.temis.presenter.LawsContract;
import com.sjcdigital.temis.presenter.LawsPresenter;
import com.sjcdigital.temis.view.adapter.AuthorAdapter;
import com.sjcdigital.temis.view.adapter.LawsAdapter;
import com.sjcdigital.temis.view.base.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bruno.oliveira on 17/09/2016.
 */
public class LawsActivity extends BaseActivity implements LawsContract.View {


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

    LawsContract.Presenter presenter;

    private LawsService lawsService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temis_view);
        ButterKnife.bind(this);
        lawsService = new LawsService();
        presenter = new LawsPresenter(lawsService);

        initialize();

    }

    private void initialize() {
        final String authorName = getIntent().getStringExtra("authorName");
        presenter.setView(this);
        presenter.loadLaws(authorName);
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
}
