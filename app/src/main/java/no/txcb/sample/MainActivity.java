package no.txcb.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements MainView {

    private MainActivityPresenter mainActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivityPresenter = new MainActivityPresenter();
        mainActivityPresenter.attachView(this);
        mainActivityPresenter.fetchContent();
    }


    @Override
    public void setWelcomeText(String text) {

    }
}
