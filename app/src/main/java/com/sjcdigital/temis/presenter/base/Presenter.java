package com.sjcdigital.temis.presenter.base;

import com.sjcdigital.temis.view.base.BaseView;

/**
 * Cada 'presenter' no aplicativo deve implementar a interface Presenter ou estender a classe BaseAdapter indicando essa interface
 *
 * @param <V> generico
 */
public interface Presenter<V extends BaseView> {

    void attachView(V baseView);

    void detachView();
}
