package br.com.mgalhardo.guidebook.infraestruture.storage.client;

import java.util.List;

import br.com.mgalhardo.guidebook.domain.entity.Guide;
import br.com.mgalhardo.guidebook.infraestruture.operator.WorkerOperator;
import io.paperdb.Paper;
import io.paperdb.PaperDbException;
import rx.Observable;
import rx.Subscriber;

public class GuideCache {

    public Observable<List<Guide>> set(final List<Guide> guides) {
        return Observable.create(new Observable.OnSubscribe<List<Guide>>() {
            @Override
            public void call(Subscriber<? super List<Guide>> subscriber) {
                try {
                    Paper.book().write("Guides", guides);
                    subscriber.onNext(guides);
                    subscriber.onCompleted();
                } catch (PaperDbException e) {
                    subscriber.onError(e);
                }
            }
        }).compose(new WorkerOperator<>());
    }

    public Observable<List<Guide>> get() {
        return Observable.create(new Observable.OnSubscribe<List<Guide>>() {
            @Override
            public void call(Subscriber<? super List<Guide>> subscriber) {
                List<Guide> guides = Paper.book().read("Guides");
                if (guides != null) {
                    subscriber.onNext(guides);
                    subscriber.onCompleted();
                } else {
                    subscriber.onError(new NullPointerException());
                }
            }
        }).compose(new WorkerOperator<>());
    }
}
