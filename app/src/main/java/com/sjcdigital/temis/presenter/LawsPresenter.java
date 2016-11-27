package com.sjcdigital.temis.presenter;

import com.google.gson.Gson;
import com.sjcdigital.temis.domain.model.Author;
import com.sjcdigital.temis.domain.model.LawList;
import com.sjcdigital.temis.domain.service.LawsService;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by bruno.santiago on 17/09/2016.
 */
public class LawsPresenter implements LawsContract.Presenter {


    private LawsContract.View view;
    private LawsService service;
    private List<LawList> lawAggregation;

    public LawsPresenter(LawsService service) {
        this.service = service;
    }

    @Override
    public List<LawList> onSaveInstanceState() {
        return lawAggregation;
    }

    @Override
    public void loadLaws(final Author author) {
        view.showLoadingLayout();
        this.lawAggregation = service.laws(author);
        if (lawAggregation.isEmpty()) {
            apiConsume(author);
        } else {
            refreshUi();
        }
    }
    public void apiConsume(final Author author) {
        view.showLoadingLayout();
        service.getLaws(author).subscribe(aggregations -> {
            this.lawAggregation = aggregations.embedded.lawList;
            service.save(lawAggregation,author);
            refreshUi();
        }, throwable -> {
            view.showErrorLayout();
            throwable.printStackTrace();
        });
    }

    @Override
    public void refreshUi() {
        if (lawAggregation != null && lawAggregation.isEmpty()) {
            view.showEmptyLayout();
        } else {
            view.showSuccessLayout();
            view.setupLaws(lawAggregation);
        }
    }

    @Override
    public void retryLaws(final Author authorName) {
        loadLaws(authorName);
    }

    @Override
    public void onLoadInstanceState(List<LawList> aggregation) {
        this.lawAggregation = aggregation;
    }

    @Override
    public void setView(LawsContract.View view) {
        this.view = view;
    }
}
