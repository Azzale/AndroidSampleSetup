package no.txcb.sample.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import no.txcb.sample.BuildConfig;
import no.txcb.sample.api.AndroidRetroApi;
import no.txcb.sample.api.RetroApiContainer;
import no.txcb.sample.login.FakeLoginApi;
import no.txcb.sample.login.RealLoginApi;
import no.txcb.sample.login.loginApi;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

@Module
public class MainModule {

    @Provides
    public loginApi provideDataApi(RealLoginApi realDataApi, FakeLoginApi fakeDataApi) {
        if (BuildConfig.DUMMY) {
            return fakeDataApi;
        } else {
            return realDataApi;
        }
    }

    @Provides
    public RetroApiContainer provideApiContainer(OkHttpClient okHttpClient) {
        return new AndroidRetroApi(okHttpClient, "http://txcb.io:3000");
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient() {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

}
