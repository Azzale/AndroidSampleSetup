package no.txcb.sample.modules;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

public class AndroidRetroApi implements RetroApiContainer {

    private RetroApiDefinition api;

    public AndroidRetroApi(OkHttpClient okHttpClient, String url) {
        api = createApiClient(okHttpClient, url);
    }

    private RetroApiDefinition createApiClient(OkHttpClient okHttpClient, String url) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();
       return retrofit.create(RetroApiDefinition.class);
    }


    @Override
    public Observable<ProfileData> getProfileData() {
        return api.getProfile();
    }
}
