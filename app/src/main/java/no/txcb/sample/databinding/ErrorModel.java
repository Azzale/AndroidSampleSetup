package no.txcb.sample.databinding;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

public class ErrorModel {
    public final ObservableBoolean errorOccurred = new ObservableBoolean(false);
    public final ObservableBoolean showProgress = new ObservableBoolean(false);
    public final ObservableField<String> errorMessage = new ObservableField<>();
}
