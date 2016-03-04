package br.com.mgalhardo.guidebook.data.repository;

import br.com.mgalhardo.guidebook.core.aggregation.GuideAggregation;
import rx.Observable;

public interface GuideRepository {

    Observable<GuideAggregation> getCompanyAggregation();
    Observable<GuideAggregation> synchronize();

}
