package no.txcb.sample.login;

import javax.inject.Inject;

import rx.Observable;

public class FakeLoginApi implements loginApi {
    @Inject
    public FakeLoginApi() {
        super();
    }

    @Override
    public Observable<Boolean> loginUser(String username, String password) {
        return Observable.just(true);
    }

    @Override
    public void clearCache() {

    }
}
