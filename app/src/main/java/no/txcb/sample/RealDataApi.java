package no.txcb.sample;

import rx.Observable;

public class RealDataApi implements DataApi {
    @Override
    public Observable<String> getTitleObservable() {
        throw new UnsupportedOperationException("Needs to be implemented");
    }
}
