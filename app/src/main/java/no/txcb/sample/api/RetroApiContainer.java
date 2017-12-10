package no.txcb.sample.api;

import java.util.List;

import io.reactivex.Observable;
import no.txcb.sample.comments.models.Comment;
import retrofit2.Call;

public interface RetroApiContainer {

    Observable<ProfileData> getProfileData();

    Observable<List<Comment>> getComments();

    Observable<Comment> postComment(Comment comment);

    Call<Comment> deleteComment(long id);
}
