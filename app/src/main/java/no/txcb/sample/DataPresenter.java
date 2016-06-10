package no.txcb.sample;

import javax.inject.Inject;

import no.txcb.sample.data.DataApi;
import no.txcb.sample.tools.RxAssist;

public class DataPresenter {
    private MainView view;

    @Inject
    DataApi dataApi;

    public void attachView(MainView view) {
        this.view = view;
        MainApplication.component(view.getContext()).inject(this);
    }


    public void buttonClicked() {
        dataApi.getProfileName()
                .compose(RxAssist.applyDefaultSchedulers())
                .subscribe(text -> view.setWelcomeText(text));
    }

    public void loginUser(String username, String password) {
        dataApi.loginUser(username, password)
            .compose(RxAssist.applyDefaultSchedulers())
        .subscribe(aBoolean -> {
            if(aBoolean){
                view.setWelcomeText("Successfully logged in");
            }else{
                view.setWelcomeText("Failed to log in");
            }
        })
        ;

    }
}
