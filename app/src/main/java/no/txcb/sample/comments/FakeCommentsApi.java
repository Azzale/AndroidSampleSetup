package no.txcb.sample.comments;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import no.txcb.sample.comments.models.Comment;
import rx.Observable;

public class FakeCommentsApi implements CommentsApi {

    @Inject
    public FakeCommentsApi() {

    }

    @Override
    public Observable<List<Comment>> getComments() {
        ArrayList<Comment> comments = new ArrayList<>();
        comments.add(new Comment(1, "First!"));
        comments.add(new Comment(2, "aww.."));
        comments.add(new Comment(3, "lame"));
        return Observable.just(comments);
    }
}
