package br.com.mgalhardo.guidebook.ui.guidelist;

import br.com.mgalhardo.guidebook.core.aggregation.GuideAggregation;
import br.com.mgalhardo.guidebook.data.repository.GuideRepository;
import rx.Subscriber;

public class GuideListPresenter implements GuideListContract.Presenter {

    private GuideListContract.View view;
    private GuideRepository repository;
    private GuideAggregation aggregation;

    public GuideListPresenter(GuideListContract.View view, GuideRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public GuideAggregation onSaveInstanceState() {
        return aggregation;
    }

    @Override
    public void onLoadInstanceState(GuideAggregation aggregation) {
        this.aggregation = aggregation;
    }

    @Override
    public void loadGuides() {
        view.showLoadingLayout();
        repository.synchronize().subscribe(new Subscriber<GuideAggregation>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                view.showErrorLayout();
            }

            @Override
            public void onNext(GuideAggregation aggregation) {
                GuideListPresenter.this.aggregation = aggregation;
                refreshUi();
            }
        });
    }

    @Override
    public void refreshUi() {
        if (aggregation != null && aggregation.guides.isEmpty()) {
            view.showEmptyLayout();
        } else {
            view.showSuccessLayout();
            view.setupGuideList(aggregation.guides);
        }
    }

    @Override
    public void retryButtonClick() {
        view.showLoadingLayout();
        loadGuides();
    }

}
