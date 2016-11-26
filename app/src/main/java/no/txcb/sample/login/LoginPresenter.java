package no.txcb.sample.login;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import no.txcb.sample.MainApplication;

public class LoginPresenter {
    private LoginView view;

    private CompositeDisposable compositeSubscription = new CompositeDisposable();

    @Inject
    LoginApi loginApi;

    void attachView(LoginView view) {
        this.view = view;
        MainApplication.component(view.getContext()).inject(this);
    }

    public void startLoginFlow() {
        view.showProgress(true);
        loginApi.checkIfUserLoggedIn()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userLoggedIn -> {
                    view.showProgress(false);
                    if (userLoggedIn) {
                        view.loginCompleted();
                    } else {
                        view.startThirdPartyLogin();
                    }
                }, getErrorLoggingInConsumer());
    }


    @NonNull
    private Consumer<Throwable> getErrorLoggingInConsumer() {
        return throwable -> {
            view.showProgress(false);
            view.setErrorText("Error occurred while logging in");
        };
    }

    void stop() {
        compositeSubscription.clear();
    }
}
