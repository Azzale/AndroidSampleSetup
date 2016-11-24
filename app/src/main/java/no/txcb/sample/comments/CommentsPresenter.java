package no.txcb.sample.comments;

import java.net.UnknownHostException;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import no.txcb.sample.MainApplication;

public class CommentsPresenter {

    private CommentsView view;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    @Inject
    CommentsApi commentsApi;

    CommentsPresenter() {

    }

    void attachView(CommentsView view) {
        this.view = view;
        MainApplication.component(view.getContext()).inject(this);
    }


    void loadComments() {
        Disposable subscribe = commentsApi.getComments()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .flatMap(Observable::fromIterable)
                .map(comment -> comment.body)
                .toList()
                .subscribe(comments -> view.showComments(comments), throwable -> {
                    if (throwable instanceof UnknownHostException) {
                        view.setError("Missing network");
                    }
                });

        compositeDisposable.add(subscribe);
    }
}
