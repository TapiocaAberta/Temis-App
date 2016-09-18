package com.sjcdigital.temis.domain.service;

import com.sjcdigital.temis.domain.api.ITemis;
import com.sjcdigital.temis.domain.api.TemisApi;
import com.sjcdigital.temis.domain.model.Embedded;
import com.sjcdigital.temis.domain.model.LawList;
import com.sjcdigital.temis.domain.model.Laws;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by bruno.oliveira on 17/09/2016.
 */
public class LawsService implements Observable.Transformer<Laws, Laws> {
    private ITemis iTemis;

    public LawsService() {
        this.iTemis = TemisApi.getRetrofit().create(ITemis.class);
    }


    public Observable<Laws> getLaws(final String authorName) {
        return iTemis.findAldermanLaws(authorName).compose(this);
    }

    @Override
    public Observable<Laws> call(Observable<Laws> tObservable) {
        return tObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
