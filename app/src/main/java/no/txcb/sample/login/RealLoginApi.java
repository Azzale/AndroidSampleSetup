package no.txcb.sample.login;

import javax.inject.Inject;

import io.reactivex.Observable;
import no.txcb.sample.api.RetroApiContainer;
import no.txcb.sample.tools.ObservableCache;

public class RealLoginApi implements LoginApi {

    private RetroApiContainer retroApiContainer;
    private ObservableCache observableCache;

    @Inject
    public RealLoginApi(RetroApiContainer retroApiContainer, ObservableCache observableCache) {
        this.retroApiContainer = retroApiContainer;
        this.observableCache = observableCache;
    }

    @Override
    public Observable<Boolean> loginUser(String username, String password) {
        return retroApiContainer.getProfileData().map(profileData -> profileData.name.equals("Navn Navnesen"));
    }

    @Override
    public void clearCache() {
        observableCache.clearCache();
    }
}
