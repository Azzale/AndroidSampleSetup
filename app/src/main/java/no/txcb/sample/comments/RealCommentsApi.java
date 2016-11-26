package no.txcb.sample.comments;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import no.txcb.sample.api.RetroApiContainer;
import no.txcb.sample.comments.models.Comment;

public class RealCommentsApi implements CommentsApi {


    private RetroApiContainer retroApiContainer;

    @Inject
    public RealCommentsApi(RetroApiContainer retroApiContainer) {
        this.retroApiContainer = retroApiContainer;
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
