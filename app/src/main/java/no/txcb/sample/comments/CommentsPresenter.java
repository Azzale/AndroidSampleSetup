package no.txcb.sample.comments;

import java.net.UnknownHostException;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import no.txcb.sample.MainApplication;
import no.txcb.sample.comments.models.Comment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(Observable::fromIterable)
                .toList()
                .subscribe(comments -> view.showComments(comments), throwable -> {
                    if (throwable instanceof UnknownHostException) {
                        view.setError("Missing network");
                    }
                });

        compositeDisposable.add(subscribe);
    }

    void postComment(String comment) {
        Disposable subscribe = commentsApi.postComment(comment)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(newComment -> view.onCommentAdded(newComment), throwable -> {
                    if (throwable instanceof UnknownHostException) {
                        view.setError("Missing network");
                    }
                });

        compositeDisposable.add(subscribe);
    }

    public void deleteComment(long id) {
        Call<Comment> call = commentsApi.deleteComment(id);
        call.enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {

                if(response.isSuccessful()) {
                    view.onCommentAdded(null);
                }
            }

            @Override
            public void onFailure(Call<Comment> call, Throwable t) {
                view.setError(""+t.getLocalizedMessage());
            }
        });
    }
}
