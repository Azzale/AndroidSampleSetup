package no.txcb.sample.login;

import android.content.Context;

public interface LoginView {

    void setWelcomeText(String text);

    Context getContext();

    void loginCompleted();

    void showProgress(boolean show);

    void setErrorText(String text);
}
