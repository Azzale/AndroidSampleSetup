package no.txcb.sample.api;

import retrofit2.http.GET;
import rx.Observable;

public interface RetroApiDefinition {
    @GET("/profile")
    Observable<ProfileData> getProfile();
}
