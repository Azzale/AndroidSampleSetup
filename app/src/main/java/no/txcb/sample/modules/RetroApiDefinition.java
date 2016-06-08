package no.txcb.sample.modules;

import retrofit2.http.GET;
import rx.Observable;

public interface RetroApiDefinition {
    @GET("/profile")
    Observable<ProfileData> getProfile();
}
