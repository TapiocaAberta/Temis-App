package com.sjcdigital.temis.domain.service;

import android.content.Context;

import com.sjcdigital.temis.domain.api.ITemis;
import com.sjcdigital.temis.domain.api.TemisApi;
import com.sjcdigital.temis.domain.database.repository.AuthorRepository;
import com.sjcdigital.temis.domain.model.Alderman;
import com.sjcdigital.temis.domain.model.Author;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AuthorService implements Observable.Transformer<Alderman, Alderman> {

    private ITemis iTemis;
    private AuthorRepository repository;

    public AuthorService(final Context context) {
        this.iTemis = TemisApi.getRetrofit().create(ITemis.class);
        this.repository = AuthorRepository.getInstance(context);
    }

    public Author getAuthor(final String id) {
        return repository.findById(id);
    }

    public void save(final List<Author> authors) {
        repository.saveList(authors);
    }

    public List<Author> authors() {
        List<Author> authors = repository.getAll();
        return authors;
    }

    public Observable<Alderman> totalPage() {
        return iTemis.findAldermans().compose(this);
    }

    public Observable<Alderman> getAldermains(final int page, final int size) {
        return iTemis.findNextAldermans(page, size).compose(this);
    }

    @Override
    public Observable<Alderman> call(Observable<Alderman> tObservable) {
        return tObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
