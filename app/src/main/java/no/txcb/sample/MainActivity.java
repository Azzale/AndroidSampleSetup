package no.txcb.sample;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainView {

    private DataPresenter dataPresenter;

    @Bind(R.id.welcome_text)
    TextView welcomeText;

    @OnClick(R.id.button)
    public void clickButton() {
        dataPresenter.buttonClicked();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        dataPresenter = new DataPresenter();
        dataPresenter.attachView(this);
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
