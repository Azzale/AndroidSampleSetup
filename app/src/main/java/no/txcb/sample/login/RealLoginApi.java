package no.txcb.sample.login;

import android.util.Log;
import android.widget.Toast;

import com.android.annotations.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;
import no.txcb.sample.api.RetroApiContainer;
import no.txcb.sample.tools.ObservableCache;

import static com.google.android.gms.internal.zzs.TAG;
import static io.reactivex.Observable.*;

public class RealLoginApi implements LoginApi {

    private ObservableCache observableCache;

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    private FirebaseAuth.AuthStateListener mAuthListener;

    @Inject
    public RealLoginApi(ObservableCache observableCache) {
        this.observableCache = observableCache;


    }

    @Override
    public Observable<Boolean> loginUser(String username, String password) {

        return create(e ->
                mAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(task -> {
                e.onNext(task.isSuccessful());
                e.onComplete();
            }));
    }

    @Override
    public Observable<Boolean> checkIfUserLoggedIn() {
        return create(e -> {
            mAuthListener = firebaseAuth -> {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    e.onNext(true);
                    e.onComplete();
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    e.onNext(false);
                    e.onComplete();
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            };
            mAuth.addAuthStateListener(mAuthListener);

        });
    }


    @Override
    public void clearCache() {
        observableCache.clearCache();
    }
}
