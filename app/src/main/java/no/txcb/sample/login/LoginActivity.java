package no.txcb.sample.login;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
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

    @Bind(R.id.welcome_text)
    TextView welcomeText;

    @Bind(R.id.username)
    EditText userName;

    @Bind(R.id.password)
    EditText password;

    @Bind(R.id.button)
    Button loginButton;


    private ErrorModel errorModel;


    @OnClick(R.id.button)
    void clickButton() {
        loginPresenter.loginUser(userName.getText().toString(), password.getText().toString());

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Presenter
        loginPresenter = new loginPresenter();
        loginPresenter.attachView(this);

        // Databinding
        ViewDataBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        errorModel = new ErrorModel();
        ((ActivityLoginBinding) binding).setErrorModel(errorModel);
        ButterKnife.bind(this);


        PublishSubject<String> usernameSubject = PublishSubject.create();
        PublishSubject<String> passwordSubject = PublishSubject.create();

        RxAssist.setOnTextChanged(userName, usernameSubject);
        RxAssist.setOnTextChanged(password, passwordSubject);
        Subscription subscribe = Observable.combineLatest(usernameSubject, passwordSubject,
                (username, password) -> username.length() > 5 && password.length() > 5)
                .subscribe(aBoolean -> loginButton.setEnabled(aBoolean));
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
        welcomeText.setText(text);
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
        password.setError(text);
    }
}
