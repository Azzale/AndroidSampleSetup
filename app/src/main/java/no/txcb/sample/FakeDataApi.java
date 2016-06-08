package no.txcb.sample;

import rx.Observable;

public class FakeDataApi implements DataApi {
    @Override
    public Observable<String> getTitleObservable() {
        return Observable.just("A new welcome text!");
    }
}
