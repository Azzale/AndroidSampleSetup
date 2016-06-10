package no.txcb.sample.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import no.txcb.sample.BuildConfig;
import no.txcb.sample.FakeDataApi;
import no.txcb.sample.api.AndroidRetroApi;
import no.txcb.sample.api.RetroApiContainer;
import no.txcb.sample.data.DataApi;
import no.txcb.sample.data.RealDataApi;
import okhttp3.OkHttpClient;

@Module
public class MainModule {

    @Provides
    public DataApi provideDataApi(RetroApiContainer retroApiContainer) {
        if (BuildConfig.DUMMY) {
            return new FakeDataApi();
        } else {
            return new RealDataApi(retroApiContainer);
        }
    }

    @Provides
    public RetroApiContainer provideApiContainer(OkHttpClient okHttpClient) {
        return new AndroidRetroApi(okHttpClient, "http://txcb.io:3000");
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .build();
    }

}
