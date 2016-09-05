package com.sjcdigital.temis.presenter;

import com.sjcdigital.temis.domain.model.Author;

import java.util.List;

public interface MainContract {

    interface View {
        void setupAuthors(List<Author> authors);

        void showLoadingLayout();

        void showErrorLayout();

        void showSuccessLayout();

        void showEmptyLayout();
    }

    interface Presenter {
        List<Author> onSaveInstanceState();

        void onLoadInstanceState(List<Author> aggregation);

        void loadAldermain();

        void refreshUi();

        void retryAldermain();

        void setView(MainContract.View view);
    }
}
