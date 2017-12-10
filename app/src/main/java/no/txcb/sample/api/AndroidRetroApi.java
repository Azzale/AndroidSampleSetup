package no.txcb.sample.api;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.List;

import io.reactivex.Observable;
import no.txcb.sample.comments.models.Comment;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AndroidRetroApi implements RetroApiContainer {

    private RetroApiDefinition api;

    public AndroidRetroApi(OkHttpClient okHttpClient, String url) {
        api = createApiClient(okHttpClient, url);
    }

    private RetroApiDefinition createApiClient(OkHttpClient okHttpClient, String url) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
       return retrofit.create(RetroApiDefinition.class);
    }


    @Override
    public Observable<ProfileData> getProfileData() {
        return api.getProfile();
    }

    @Override
    public Observable<List<Comment>> getComments() {
        return api.getComments();
    }

    @Override
    public Observable<Comment> postComment(Comment comment) {
       return api.postComment(comment);
    }

    @Override
    public Call<Comment> deleteComment(long id) {
        return api.deleteComment(id);
    }
}
