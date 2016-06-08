package no.txcb.sample;

import no.txcb.sample.tools.RxAssist;
import rx.Observable;

public class MainActivityPresenter {
    private MainView view;

    public void attachView(MainView view) {
        this.view = view;
    }

    public void fetchContent() {
        Observable.just("New text after button")
        .compose(RxAssist.applyDefaultSchedulers())
        .subscribe(text -> view.setWelcomeText(text));
    }
}
