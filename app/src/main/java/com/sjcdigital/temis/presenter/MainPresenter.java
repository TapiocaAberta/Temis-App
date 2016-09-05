package com.sjcdigital.temis.presenter;

import com.sjcdigital.temis.domain.model.Author;
import com.sjcdigital.temis.domain.service.AuthorService;

import java.util.List;

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View view;
    private AuthorService service;
    private List<Author> authorAggregation;

    public MainPresenter(AuthorService service) {
        this.service = service;
    }

    @Override
    public List<Author> onSaveInstanceState() {
        return authorAggregation;
    }

    @Override
    public void onLoadInstanceState(List<Author> aggregation) {
        this.authorAggregation = aggregation;
    }

    @Override
    public void loadAldermain() {
        view.showLoadingLayout();
        service.getAldermains().subscribe(aggregations -> {
            this.authorAggregation = aggregations;
            refreshUi();
        }, throwable -> {
            view.showErrorLayout();
            throwable.printStackTrace();
        });
    }

    @Override
    public void refreshUi() {
        if (authorAggregation != null && authorAggregation.isEmpty()) {
            view.showEmptyLayout();
        } else {
            view.showSuccessLayout();
            view.setupAuthors(authorAggregation);
        }
    }

    @Override
    public void retryAldermain() {
        loadAldermain();
    }

    @Override
    public void setView(MainContract.View view) {
        this.view = view;
    }
}
