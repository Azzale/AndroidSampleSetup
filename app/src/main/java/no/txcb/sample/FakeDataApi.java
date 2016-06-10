package no.txcb.sample;

import no.txcb.sample.data.DataApi;
import rx.Observable;

public class FakeDataApi implements DataApi {
    @Override
    public Observable<String> getProfileName() {
        return Observable.just("A new welcome text!");
    }

    @Override
    public Observable<Boolean> loginUser(String username, String password) {
        return Observable.just(true);
    }
}
