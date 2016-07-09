package no.txcb.sample.login;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import no.txcb.sample.R;
import no.txcb.sample.databinding.ActivityLoginBinding;
import no.txcb.sample.databinding.ErrorModel;
import no.txcb.sample.tools.RxAssist;
import rx.Observable;
import rx.Subscription;
import rx.subjects.PublishSubject;
import rx.subscriptions.CompositeSubscription;

public class LoginActivity extends AppCompatActivity implements loginView {

    private no.txcb.sample.login.loginPresenter loginPresenter;

    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    private ErrorModel errorModel;
    private ActivityLoginBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Presenter
        loginPresenter = new loginPresenter();
        loginPresenter.attachView(this);

        // Databinding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        errorModel = new ErrorModel();
        binding.setErrorModel(errorModel);

        binding.loginContent.button.setOnClickListener(view ->
                loginPresenter.loginUser(binding.loginContent.username.getText().toString(), binding.loginContent.password.getText().toString()));
        PublishSubject<String> usernameSubject = PublishSubject.create();
        PublishSubject<String> passwordSubject = PublishSubject.create();

        RxAssist.setOnTextChanged(binding.loginContent.username, usernameSubject);
        RxAssist.setOnTextChanged(binding.loginContent.password, passwordSubject);
        Subscription subscribe = Observable.combineLatest(usernameSubject, passwordSubject,
                (username, password) -> username.length() > 5 && password.length() > 5)
                .subscribe(binding.loginContent.button::setEnabled);
        compositeSubscription.add(subscribe);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter.stop();
        compositeSubscription.unsubscribe();
    }

    @Override
    public void setWelcomeText(String text) {
        binding.loginContent.welcomeText.setText(text);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void loginCompleted() {
        finish();
    }

    @Override
    public void showProgress(boolean show) {
        errorModel.showProgress.set(show);
    }

    @Override
    public void setErrorText(String text) {
        binding.loginContent.password.setError(text);
    }
}
