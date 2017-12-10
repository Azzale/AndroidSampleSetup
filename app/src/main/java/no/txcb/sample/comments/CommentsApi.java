package no.txcb.sample.comments;

import java.util.List;

import io.reactivex.Observable;
import no.txcb.sample.comments.models.Comment;
import retrofit2.Call;

public interface CommentsApi {
    Observable<List<Comment>> getComments();

    Observable<Comment> postComment(String comment);

    Call<Comment> deleteComment(long id);
}
