package br.com.mgalhardo.guidebook.presentation.ui.guidelist;

import java.util.List;

import br.com.mgalhardo.guidebook.domain.aggregation.GuideAggregation;
import br.com.mgalhardo.guidebook.domain.entity.Guide;

public interface GuideListContract {

    interface View {
        void setupGuideList(List<Guide> companies);

        void showLoadingLayout();

        void showErrorLayout();

        void showSuccessLayout();

        void showEmptyLayout();
    }

    interface Presenter {
        GuideAggregation onSaveInstanceState();

        void onLoadInstanceState(GuideAggregation aggregation);

        void loadGuides();

        void refreshUi();

        void retryButtonClick();

        void setView(GuideListContract.View view);
    }
}
