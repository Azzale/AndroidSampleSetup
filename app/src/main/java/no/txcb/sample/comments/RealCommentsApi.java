package no.txcb.sample.comments;

import java.util.List;

import javax.inject.Inject;

import no.txcb.sample.api.RetroApiContainer;
import no.txcb.sample.comments.models.Comment;
import rx.Observable;

public class RealCommentsApi implements CommentsApi {


    private RetroApiContainer retroApiContainer;

    @Inject
    public RealCommentsApi(RetroApiContainer retroApiContainer) {
        this.retroApiContainer = retroApiContainer;
    }

    @Override
    public Observable<List<Comment>> getComments() {
        return retroApiContainer.getComments();
    }
}
