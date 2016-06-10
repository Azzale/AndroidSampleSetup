package no.txcb.sample;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import no.txcb.sample.tools.RxAssist;
import rx.Observable;
import rx.subjects.PublishSubject;

public class MainActivity extends AppCompatActivity implements MainView {

    private DataPresenter dataPresenter;

    @Bind(R.id.welcome_text)
    TextView welcomeText;

    @Bind(R.id.username)
    EditText userName;

    @Bind(R.id.password)
    EditText password;

    @Bind(R.id.button)
    Button loginButton;


    @OnClick(R.id.button)
    void clickButton() {
        dataPresenter.loginUser(userName.getText().toString(), password.getText().toString());

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        dataPresenter = new DataPresenter();
        dataPresenter.attachView(this);


        PublishSubject<String> usernameSubject = PublishSubject.create();
        PublishSubject<String> passwordSubject = PublishSubject.create();

        RxAssist.setOnTextChanged(userName, usernameSubject);
        RxAssist.setOnTextChanged(password, passwordSubject);
        Observable.combineLatest(usernameSubject, passwordSubject,
                (username, password) -> username.length() > 5 && password.length() > 5)
                .subscribe(aBoolean -> loginButton.setEnabled(aBoolean));
    }


    @Override
    public void setWelcomeText(String text) {
        welcomeText.setText(text);
    }

    @Override
    public Context getContext() {
        return this;
    }
}
