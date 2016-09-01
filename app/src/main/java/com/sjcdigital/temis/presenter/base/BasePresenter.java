package com.sjcdigital.temis.presenter.base;

import com.sjcdigital.temis.view.base.BaseView;

/**
 * Classe que implementa a interface Presenter e os métodos attachView e detachView
 *
 * @param <T> generico
 */
public class BasePresenter<T extends BaseView> implements Presenter<T> {

    private T mBaseView;

    @Override
    public void attachView(T baseView) {
        mBaseView = baseView;
    }

    @Override
    public void detachView() {
        mBaseView = null;
    }

    public boolean isViewAttached() {
        return mBaseView != null;
    }

    public T getBaseView() {
        return mBaseView;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new BaseViewNotAttachedException();
    }

    public static class BaseViewNotAttachedException extends RuntimeException {
        public BaseViewNotAttachedException() {
            super("Antes de solicitar dados para o Presenter inicie a chamada de Presenter.attachView(BaseView)");
        }
    }
}

