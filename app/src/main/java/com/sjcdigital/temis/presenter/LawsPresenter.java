package com.sjcdigital.temis.presenter;

import com.sjcdigital.temis.domain.model.LawList;
import com.sjcdigital.temis.domain.service.LawsService;

import java.util.List;

/**
 * Created by bruno.oliveira on 17/09/2016.
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
    public void loadLaws(final String authorName) {
        view.showLoadingLayout();
        service.getLaws(authorName).subscribe(aggregations -> {
            this.lawAggregation = aggregations.embedded.lawList;
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
    public void retryLaws(final String authorName) {
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
