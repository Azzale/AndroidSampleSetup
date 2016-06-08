package no.txcb.sample;

import rx.Observable;

public interface DataApi {
    Observable<String> getTitleObservable();
}
