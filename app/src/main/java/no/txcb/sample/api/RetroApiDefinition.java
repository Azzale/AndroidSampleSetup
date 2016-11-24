package no.txcb.sample.api;

import java.util.List;

import io.reactivex.Observable;
import no.txcb.sample.comments.models.Comment;
import retrofit2.http.GET;

public interface RetroApiDefinition {
    @GET("/profile")
    Observable<ProfileData> getProfile();

    @GET("/comments")
    Observable<List<Comment>> getComments();
}
