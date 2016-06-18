package no.txcb.sample.login;

import rx.Observable;

public interface loginApi {

    Observable<Boolean> loginUser(String username, String password);

    void clearCache();
}
