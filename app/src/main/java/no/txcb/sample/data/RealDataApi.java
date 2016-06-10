package no.txcb.sample.data;

import no.txcb.sample.api.RetroApiContainer;
import rx.Observable;

public class RealDataApi implements DataApi {

    private RetroApiContainer retroApiContainer;

    public RealDataApi(RetroApiContainer retroApiContainer) {
        this.retroApiContainer = retroApiContainer;
    }

    @Override
    public Observable<String> getProfileName() {
        return retroApiContainer.getProfileData().map(profileData -> profileData.name);
    }
}
