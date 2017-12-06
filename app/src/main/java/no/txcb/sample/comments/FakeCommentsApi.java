package no.txcb.sample.comments;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import no.txcb.sample.comments.models.Comment;

public class FakeCommentsApi implements CommentsApi {

    @Inject
    public FakeCommentsApi() {

    }

    @Override
    public Observable<List<Comment>> getComments() {
        ArrayList<Comment> comments = new ArrayList<>();
        comments.add(new Comment("First!"));
        comments.add(new Comment("aww.."));
        comments.add(new Comment("lame"));
        return Observable.just(comments);
    }
}
