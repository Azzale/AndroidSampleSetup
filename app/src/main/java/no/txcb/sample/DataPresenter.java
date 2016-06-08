package no.txcb.sample;

import javax.inject.Inject;

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
        dataApi.getTitleObservable()
                .compose(RxAssist.applyDefaultSchedulers())
                .subscribe(text -> view.setWelcomeText(text));
    }
}
