package no.txcb.sample.login;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;
import no.txcb.sample.R;
import no.txcb.sample.comments.CommentsActivity;
import no.txcb.sample.databinding.ActivityLoginBinding;
import no.txcb.sample.databinding.ErrorModel;
import no.txcb.sample.tools.RxAssist;

public class LoginActivity extends AppCompatActivity implements LoginView {

    private LoginPresenter LoginPresenter;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private ErrorModel errorModel;
    private ActivityLoginBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Presenter
        LoginPresenter = new LoginPresenter();
        LoginPresenter.attachView(this);

        // Databinding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        errorModel = new ErrorModel();
        binding.setErrorModel(errorModel);

        binding.loginContent.button.setOnClickListener(view ->
                LoginPresenter.loginUser(binding.loginContent.username.getText().toString(), binding.loginContent.password.getText().toString()));
        PublishSubject<String> usernameSubject = PublishSubject.create();
        PublishSubject<String> passwordSubject = PublishSubject.create();

        RxAssist.setOnTextChanged(binding.loginContent.username, usernameSubject);
        RxAssist.setOnTextChanged(binding.loginContent.password, passwordSubject);
        Disposable subscribe = Observable.combineLatest(usernameSubject, passwordSubject,
                (username, password) -> username.length() > 5 && password.length() > 5)
                .subscribe(binding.loginContent.button::setEnabled);
        compositeDisposable.add(subscribe);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LoginPresenter.stop();
        compositeDisposable.clear();
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
        Toast.makeText(this, "Login success!", Toast.LENGTH_SHORT).show();
        finish();
        startActivity(new Intent(this, CommentsActivity.class));
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
