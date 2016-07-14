package br.com.mgalhardo.guidebook.infraestruture.storage.client;

import br.com.mgalhardo.guidebook.domain.aggregation.GuideAggregation;
import retrofit2.http.GET;
import rx.Observable;

public interface GuideService {

    @GET("upcomingGuides")
    Observable<GuideAggregation> getGuideAggregation();
}