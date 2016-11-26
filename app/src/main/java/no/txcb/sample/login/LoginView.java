package no.txcb.sample.login;

import android.content.Context;

interface LoginView {

    Context getContext();

    void loginCompleted();

    void showProgress(boolean show);

    void setErrorText(String text);

    void startThirdPartyLogin();

}
