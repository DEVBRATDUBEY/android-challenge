package br.com.mgalhardo.guidebook.data.local;

import br.com.mgalhardo.guidebook.core.aggregation.GuideAggregation;
import io.paperdb.Paper;
import io.paperdb.PaperDbException;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GuideCache {

    public Observable<GuideAggregation> set(final GuideAggregation aggregation) {
        Observable.OnSubscribe<GuideAggregation> onSubscribe = new Observable.OnSubscribe<GuideAggregation>() {
            @Override
            public void call(Subscriber<? super GuideAggregation> subscriber) {
                try {
                    Paper.book().write(GuideAggregation.KEY, aggregation);
                    subscriber.onNext(aggregation);
                    subscriber.onCompleted();
                } catch (PaperDbException e) {
                    subscriber.onError(e);
                }
            }
        };
        return Observable.create(onSubscribe)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<GuideAggregation> get() {
        Observable.OnSubscribe<GuideAggregation> onSubscribe = new Observable.OnSubscribe<GuideAggregation>() {
            @Override
            public void call(Subscriber<? super GuideAggregation> subscriber) {
                GuideAggregation aggregation = Paper.book().read(GuideAggregation.KEY);
                if (aggregation != null) {
                    subscriber.onNext(aggregation);
                    subscriber.onCompleted();
                } else {
                    subscriber.onError(new NullPointerException());
                }
            }
        };
        return Observable.create(onSubscribe)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
