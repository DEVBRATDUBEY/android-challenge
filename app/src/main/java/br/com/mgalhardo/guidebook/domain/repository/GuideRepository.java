package br.com.mgalhardo.guidebook.domain.repository;

import br.com.mgalhardo.guidebook.domain.aggregation.GuideAggregation;
import rx.Observable;

public interface GuideRepository {

    Observable<GuideAggregation> getCompanies();
}
