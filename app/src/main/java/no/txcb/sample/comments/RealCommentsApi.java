package no.txcb.sample.comments;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import no.txcb.sample.api.RetroApiContainer;
import no.txcb.sample.comments.models.Comment;
import retrofit2.Call;

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

    @Override
    public Observable<Comment> postComment(String comment) {
        return retroApiContainer.postComment(new Comment(comment));
    }

    @Override
    public Call<Comment> deleteComment(long id) {
        return retroApiContainer.deleteComment(id);
    }
}
