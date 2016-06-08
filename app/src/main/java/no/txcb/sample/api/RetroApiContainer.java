package no.txcb.sample.api;

import rx.Observable;

public interface RetroApiContainer {

    Observable<ProfileData> getProfileData();

}
