package no.txcb.sample.api;

import java.util.List;

import io.reactivex.Observable;
import no.txcb.sample.comments.models.Comment;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetroApiDefinition {
    @GET("/profile")
    Observable<ProfileData> getProfile();

    @GET("/comments")
    Observable<List<Comment>> getComments();

    @POST("/comments")
    Observable<Comment> postComment(@Body Comment comment);

    @DELETE("/comments/{id}")
    Call<Comment> deleteComment(@Path("id") long id);
}
