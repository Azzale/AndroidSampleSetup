package no.txcb.sample.login;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import no.txcb.sample.BuildConfig;
import no.txcb.sample.MainApplication;
import no.txcb.sample.R;

public class LoginPresenter {
    private LoginView view;

    private CompositeDisposable compositeSubscription = new CompositeDisposable();

    @Inject
    LoginApi loginApi;

    public void attachView(LoginView view) {
        this.view = view;
        MainApplication.component(view.getContext()).inject(this);
    }


    public void loginUser(String username, String password) {
        view.showProgress(true);
        Disposable loginSub = loginApi.loginUser(username, password)
                .delay(5, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> {
                    view.showProgress(false);
                    if (BuildConfig.DEBUG) {
                        Log.d("SAMPLE", "Login Success!");
                    }
                    if (aBoolean) {
                        view.setWelcomeText(view.getContext().getString(R.string.successfully_logged_in));
                        view.loginCompleted();
                    } else {
                        if (BuildConfig.DEBUG) {
                            Log.d("SAMPLE", "Login fail!");
                        }
                        loginApi.clearCache();
                        view.setErrorText(view.getContext().getString(R.string.failed_to_log_in));
                    }
                });
        compositeSubscription.add(loginSub);

    }

    public void stop() {
        compositeSubscription.clear();
    }
}
