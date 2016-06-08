package no.txcb.sample;

import no.txcb.sample.data.DataApi;
import rx.Observable;

public class FakeDataApi implements DataApi {
    @Override
    public Observable<String> getTitleObservable() {
        return Observable.just("A new welcome text!");
    }
}
