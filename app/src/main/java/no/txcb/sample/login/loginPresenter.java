package no.txcb.sample.login;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import no.txcb.sample.MainApplication;
import no.txcb.sample.R;
import no.txcb.sample.tools.RxAssist;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class loginPresenter {
    private loginView view;

    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    @Inject
    loginApi loginApi;

    public void attachView(loginView view) {
        this.view = view;
        MainApplication.component(view.getContext()).inject(this);
    }


    public void loginUser(String username, String password) {
        view.showProgress(true);
        Subscription loginSub = loginApi.loginUser(username, password)
                .delay(5, TimeUnit.SECONDS)
                .compose(RxAssist.applyDefaultSchedulers())
                .subscribe(aBoolean -> {
                    view.showProgress(false);
                    if (aBoolean) {
                        view.setWelcomeText(view.getContext().getString(R.string.successfully_logged_in));
                        view.loginCompleted();
                    } else {
                        loginApi.clearCache();
                        view.setErrorText(view.getContext().getString(R.string.failed_to_log_in));
                    }
                });
        compositeSubscription.add(loginSub);

    }

    public void stop() {
        compositeSubscription.unsubscribe();
    }
}
