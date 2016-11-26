package no.txcb.sample.login;


import io.reactivex.Observable;

public interface LoginApi {

    Observable<Boolean> loginUser(String username, String password);

    void clearCache();

    Observable<Boolean> checkIfUserLoggedIn();
}
