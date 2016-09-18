package com.sjcdigital.temis.presenter;

import com.sjcdigital.temis.domain.model.LawList;
import com.sjcdigital.temis.domain.model.Laws;

import java.util.List;

/**
 * Created by bruno.oliveira on 17/09/2016.
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

        void loadLaws(final String authorName);

        void refreshUi();

        void retryLaws(final String authorName);

        void onLoadInstanceState(List<LawList> aggregation);

        void setView(LawsContract.View view);
    }
}
