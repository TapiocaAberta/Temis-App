package com.sjcdigital.temis.domain.service;

import com.sjcdigital.temis.domain.api.ITemis;
import com.sjcdigital.temis.domain.api.TemisApi;
import com.sjcdigital.temis.domain.model.Author;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AuthorService implements Observable.Transformer<List<Author>, List<Author>> {

    private ITemis iTemis;

    public AuthorService() {
        this.iTemis = TemisApi.getRetrofit().create(ITemis.class);
    }

    public Observable<List<Author>> getAldermains() {
        return iTemis.findAldermans().compose(this);
    }

    @Override
    public Observable<List<Author>> call(Observable<List<Author>> tObservable) {
        return tObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
