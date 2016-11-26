package no.txcb.sample.login;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;


import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ui.ResultCodes;

import java.util.Arrays;

import io.reactivex.disposables.CompositeDisposable;
import no.txcb.sample.R;
import no.txcb.sample.comments.CommentsActivity;
import no.txcb.sample.databinding.ActivityLoginBinding;
import no.txcb.sample.databinding.ErrorModel;

import static com.firebase.ui.auth.ui.AcquireEmailHelper.RC_SIGN_IN;


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
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        LoginPresenter.attachView(this);

        // Databinding
        errorModel = new ErrorModel();
        binding.setErrorModel(errorModel);


        LoginPresenter.startLoginFlow();
        binding.loginContent.button.setOnClickListener(view ->
                LoginPresenter.startLoginFlow());
    }

    @Override
    public void startThirdPartyLogin() {
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setProviders(Arrays.asList(new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                                new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build()))
                        .build(),
                RC_SIGN_IN);
    }

    private void snackBar(String text) {
        Snackbar.make(binding.getRoot(), text, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            // user is signed in!
            startComments();
            return;
        }

        // Sign in canceled
        if (resultCode == RESULT_CANCELED) {
            snackBar("Login cancelled");
            binding.loginContent.button.setEnabled(true);
            return;
        }

        // No network
        if (resultCode == ResultCodes.RESULT_NO_NETWORK) {
            snackBar("No network, please try again later");
            binding.loginContent.button.setEnabled(true);
            return;
        }

        // User is not signed in. Maybe just wait for the user to press
        // "sign in" again, or show a message.
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LoginPresenter.stop();
        compositeDisposable.clear();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void loginCompleted() {
        startComments();
    }

    private void startComments() {
        startActivity(new Intent(this, CommentsActivity.class));
        finish();
    }

    @Override
    public void showProgress(boolean show) {
        errorModel.showProgress.set(show);
    }

    @Override
    public void setErrorText(String text) {
        binding.loginContent.button.setEnabled(true);
        snackBar(text);
    }

}
