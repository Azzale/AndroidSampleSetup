package no.txcb.sample.data;

import rx.Observable;

public interface DataApi {
    Observable<String> getProfileName();

    Observable<Boolean> loginUser(String username, String password);
}
