package com.sjcdigital.temis.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.sjcdigital.temis.R;
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

public class MainActivity extends BaseActivity implements MainContract.View {

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

    MainContract.Presenter presenter;

    private AuthorService authorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        authorService = new AuthorService();
        presenter = new MainPresenter(authorService);

        initialize();

    }

    private void initialize() {
        presenter.setView(this);
        presenter.loadAldermain();
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
