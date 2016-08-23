package no.txcb.sample.login;

import rx.Observable;

public interface LoginApi {

    Observable<Boolean> loginUser(String username, String password);

    void clearCache();
}
