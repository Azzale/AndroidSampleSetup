package no.txcb.sample.api;

import java.util.List;

import no.txcb.sample.comments.models.Comment;
import retrofit2.http.GET;
import rx.Observable;

public interface RetroApiDefinition {
    @GET("/profile")
    Observable<ProfileData> getProfile();

    @GET("/comments")
    Observable<List<Comment>> getComments();
}
