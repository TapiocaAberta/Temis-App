package com.sjcdigital.temis.domain.service;

import android.content.Context;

import com.sjcdigital.temis.domain.api.ITemis;
import com.sjcdigital.temis.domain.api.TemisApi;
import com.sjcdigital.temis.domain.database.repository.AuthorRepository;
import com.sjcdigital.temis.domain.database.repository.LawRepository;
import com.sjcdigital.temis.domain.model.Author;
import com.sjcdigital.temis.domain.model.Law;
import com.sjcdigital.temis.domain.model.LawList;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by bruno.santiago on 17/09/2016.
 */
public class LawsService implements Observable.Transformer<Law, Law> {
    private static final int PAGE = 0;
    private static final int TOTAL = 2000;
    private ITemis iTemis;
    private LawRepository repository;

    public LawsService(final Context context) {
        this.iTemis = TemisApi.getRetrofit().create(ITemis.class);
        this.repository = LawRepository.getInstance(context);
    }

    public List<LawList> laws(final Author author) {
        List<LawList> laws = repository.findByAuthor(author);
        return laws;
    }
    public void save(final List<LawList> laws, final Author author) {
        repository.saveList(laws,author);
    }

    public Observable<Law> getLaws(final Author author) {
        return iTemis.findAldermanLaws(author.getName(),PAGE,TOTAL).compose(this);
    }

    @Override
    public Observable<Law> call(Observable<Law> tObservable) {
        return tObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
