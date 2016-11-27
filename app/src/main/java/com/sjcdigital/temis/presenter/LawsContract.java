package com.sjcdigital.temis.presenter;

import com.sjcdigital.temis.domain.model.Author;
import com.sjcdigital.temis.domain.model.LawList;

import java.util.List;

/**
 * Created by bruno.santiago on 17/09/2016.
 */
public interface LawsContract {
    interface View {
        void setupLaws(List<LawList> laws);

        void showLoadingLayout();

        void showErrorLayout();

        void showSuccessLayout();

        void showEmptyLayout();
    }

    interface Presenter {
        List<LawList> onSaveInstanceState();

        void loadLaws(final Author authorName);

        void refreshUi();

        void retryLaws(final Author authorName);

        void onLoadInstanceState(List<LawList> aggregation);

        void setView(LawsContract.View view);
    }
}
