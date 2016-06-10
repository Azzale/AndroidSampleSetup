package no.txcb.sample.data;

import rx.Observable;

public interface DataApi {
    Observable<String> getProfileName();
}
