package no.txcb.sample.login;

import javax.inject.Inject;

import io.reactivex.Observable;


public class FakeLoginApi implements LoginApi {
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

    @Override
    public Observable<Boolean> checkIfUserLoggedIn() {
        return Observable.just(false);
    }
}
