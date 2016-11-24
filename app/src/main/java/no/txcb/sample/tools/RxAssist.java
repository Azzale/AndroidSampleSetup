package no.txcb.sample.tools;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import io.reactivex.subjects.PublishSubject;


public class RxAssist {

    public static void setOnTextChanged(EditText userName, final PublishSubject<String> usernameSubject) {
        userName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                usernameSubject.onNext(s.toString());
            }
        });
    }
}
