package no.txcb.sample.modules;

import rx.Observable;

public interface RetroApiContainer {

    Observable<ProfileData> getProfileData();

}
