package com.sjcdigital.temis.presenter;

import com.sjcdigital.temis.domain.model.Author;
import com.sjcdigital.temis.domain.service.AuthorService;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View view;
    private AuthorService service;
    private List<Author> authorAggregation;

    public MainPresenter(final AuthorService service) {
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

        authorAggregation = service.authors();
        if (authorAggregation.isEmpty()) {
            apiConsume();
        } else {
            refreshUi();
        }
    }

    protected void getAldermans(final int page, final int size) {
        service.getAldermains(page, size)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aggregations -> {
                    this.authorAggregation = aggregations.embedded.aldermanList;
                    service.save(authorAggregation);
                    refreshUi();
                }, throwable -> {
                    view.showErrorLayout();
                    loadAldermain();
                });
    }

    public void apiConsume() {
        view.showLoadingLayout();
        service.totalPage()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aggregations -> {
                    getAldermans(aggregations.page.number, aggregations.page.total);
                }, throwable -> {
                    view.showErrorLayout();
                    loadAldermain();
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
