package br.com.mgalhardo.guidebook.data.repository.impl;

import br.com.mgalhardo.guidebook.core.aggregation.GuideAggregation;
import br.com.mgalhardo.guidebook.data.local.GuideCache;
import br.com.mgalhardo.guidebook.data.remote.GuideService;
import br.com.mgalhardo.guidebook.data.repository.GuideRepository;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class GuideRepositoryImpl implements GuideRepository {

    private GuideService service;
    private GuideCache cache;

    public GuideRepositoryImpl(GuideService service, GuideCache cache) {
        this.service = service;
        this.cache = cache;
    }

    @Override
    public Observable<GuideAggregation> getCompanyAggregation() {
        return cache.get();
    }

    @Override
    public Observable<GuideAggregation> synchronize() {
        return service.getGuideAggregation()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .concatMap(new Func1<GuideAggregation, Observable<GuideAggregation>>() {
            @Override
            public Observable<GuideAggregation> call(GuideAggregation aggregation) {
                return cache.set(aggregation);
            }
        });
    }
}