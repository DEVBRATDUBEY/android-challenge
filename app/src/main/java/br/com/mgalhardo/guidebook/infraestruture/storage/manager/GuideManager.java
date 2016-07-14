package br.com.mgalhardo.guidebook.infraestruture.storage.manager;

import javax.inject.Inject;

import br.com.mgalhardo.guidebook.domain.aggregation.GuideAggregation;
import br.com.mgalhardo.guidebook.domain.repository.GuideRepository;
import br.com.mgalhardo.guidebook.infraestruture.operator.WorkerOperator;
import br.com.mgalhardo.guidebook.infraestruture.storage.client.GuideService;
import rx.Observable;

public class GuideManager implements GuideRepository {

    private final GuideService guideService;
    private final WorkerOperator<GuideAggregation> workerOperator;

    @Inject
    public GuideManager(GuideService guideService, WorkerOperator<GuideAggregation> workerOperator) {
        this.guideService = guideService;
        this.workerOperator = workerOperator;
    }

    @Override
    public Observable<GuideAggregation> getCompanies() {
        return guideService.getGuideAggregation().compose(workerOperator);
    }
}