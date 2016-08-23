package no.txcb.sample.comments;

import java.net.UnknownHostException;

import javax.inject.Inject;

import no.txcb.sample.MainApplication;
import no.txcb.sample.tools.RxAssist;
import rx.Observable;

public class CommentsPresenter {

    private CommentsView view;

    @Inject
    CommentsApi commentsApi;

    CommentsPresenter() {

    }

    void attachView(CommentsView view) {
        this.view = view;
        MainApplication.component(view.getContext()).inject(this);
    }


    void loadComments() {
        commentsApi.getComments().compose(RxAssist.applyDefaultSchedulers())
                .flatMap(Observable::from)
                .map(comment -> comment.body)
                .toList()
                .subscribe(comments -> {
                    view.showComments(comments);
                }, throwable -> {
                    if (throwable instanceof UnknownHostException) {
                        view.setError("Missing network");
                    }
                });
    }
}
